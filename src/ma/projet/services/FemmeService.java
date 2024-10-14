/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.services;

import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import ma.projet.beans.Femme;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class FemmeService implements IDao<Femme> {

    private Object entityManager;

    @Override
    public boolean create(Femme o) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Femme getById(int id) {
        Session session = null;
        Femme e = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            e = (Femme) session.get(Femme.class, id);
            session.getTransaction().commit();
            return e;
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return e;
    }

    @Override
    public List<Femme> getAll() {
        Session session = null;
        List<Femme> produits = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            produits = session.createQuery("from Femme").list();
            session.getTransaction().commit();
            return produits;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return produits;
    }

    public int getNbrEnfantEntreDates(Femme femme, Date dateDebut, Date dateFin) {
        Session session = null;
        Transaction tx = null;
        int nombreEnfants = 0;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("SELECT SUM(m.nbrEnfant) FROM Mariage m WHERE m.femme.id = :femmeId AND m.dateFin BETWEEN :dateDebut AND :dateFin");
            query.setParameter("femmeId", femme.getId());
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            Object result = query.uniqueResult();
            if (result != null) {
                nombreEnfants = ((Number) result).intValue();
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return nombreEnfants;
    }

    public Femme getFemmePlusAgee() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Femme femmePlusAgee = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Femme ORDER BY dateNaissance ASC");
            query.setMaxResults(1);

            femmePlusAgee = (Femme) query.uniqueResult();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return femmePlusAgee;
    }

    public List<Femme> getFemmesMarieesDeuxFoisOuPlus() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Femme> femmes = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT f "
                    + "FROM Femme f "
                    + "WHERE f IN ("
                    + "   SELECT m.femme "
                    + "   FROM Mariage m "
                    + "   GROUP BY m.femme "
                    + "   HAVING COUNT(m.femme) >= 2"
                    + ")");
            femmes = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return femmes;
    }
}

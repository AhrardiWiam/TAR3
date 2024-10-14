/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Marriage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author user
 */
public class HommeService implements IDao<Homme> {

    @Override
    public boolean create(Homme o) {
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
    public Homme getById(int id) {
        Session session = null;
        Homme e = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            e = (Homme) session.get(Homme.class, id);
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
    public List<Homme> getAll() {
        Session session = null;
        List<Homme> produits = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            produits = session.createQuery("from Homme").list();
            session.getTransaction().commit();
            return produits;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return produits;
    }

    public void HommeEpouse(Homme h, String d1, String d2) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date1 = dateFormat.parse(d1);
            Date date2 = dateFormat.parse(d2);
            System.out.println(h.getNom()+" "+h.getPrenom()+" a été marié apres "+d1+" et avant "+d2+" avec : ");;
            for (Marriage m : h.getMarriages()) {
                boolean n;
                if(m.getDateFin()==null) n=true;
                else n=m.getDateFin().before(date2);
                if(m.getId().getDateDebut().after(date1)&&n)
               {System.out.println(m.getFemme().getNom()+" "+ m.getFemme().getPrenom());} 
            }
        } catch (ParseException ex) {
            Logger.getLogger(HommeService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.test;

import java.util.Date;
import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Marriage;
import ma.projet.beans.MarriagePK;
import ma.projet.services.FemmeService;
import ma.projet.services.HommeService;
import ma.projet.services.MarriageService;
import ma.projet.util.HibernateUtil;

/**
 *
 * @author user
 */
public class Test {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
        FemmeService fs = new FemmeService();
        HommeService hs= new HommeService();
        MarriageService ms= new MarriageService();
        //Création des femmes
//        fs.create(new Femme("Ahrardi", "Wiam", "0673452910","Casablanca" , new Date(103,3,14)));
//        fs.create(new Femme("benaasi", "Lamia", "0656452309","Agadir" , new Date(102,6,7)));
        //Création des hommes
//        hs.create(new Homme("xxxxxx", "xxxxxx", "0791328740","Casablanca" , new Date(100,3,14)));
//        hs.create(new Homme("Anouar", "berrada", "0791328740","Rabat" , new Date(100,3,14)));
//        
        //Créeation des Marriages
//        ms.create(new Marriage(new MarriagePK(3, 1,new Date(122,2,18)),new Date(123,9,7), 0));
//        ms.create(new Marriage(new MarriagePK(3, 2,new Date(124,0,30)),null, 1));
//        ms.create(new Marriage(new MarriagePK(4, 1,new Date(124,6,23)),null, 0));
//        
        hs.HommeEpouse(hs.getById(4), "2023/03/12", "2025/10/10");

    }
   
}

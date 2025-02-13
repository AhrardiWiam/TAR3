/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
public class Marriage {
    @EmbeddedId
    private MarriagePK id;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private int nbrEnfant;
    @ManyToOne
    @JoinColumn(name = "homme",insertable = false,updatable = false)
    private Homme homme;
    @ManyToOne
    @JoinColumn(name = "femme",insertable = false,updatable = false)
    private Femme femme;
    public Marriage() {
    }

    public Marriage(MarriagePK id, Date dateFin, int nbrEnfant) {
        this.id = id;
        this.dateFin = dateFin;
        this.nbrEnfant = nbrEnfant;
    }
    
    public MarriagePK getId() {
        return id;
    }

    public void setId(MarriagePK id) {
        this.id = id;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbrEnfant() {
        return nbrEnfant;
    }

    public void setNbrEnfant(int nbrEnfant) {
        this.nbrEnfant = nbrEnfant;
    }

    public Homme getHomme() {
        return homme;
    }

    public void setHomme(Homme homme) {
        this.homme = homme;
    }

    public Femme getFemme() {
        return femme;
    }

    public void setFemme(Femme femme) {
        this.femme = femme;
    }

    @Override
    public String toString() {
        return "Marriage de :l'homme : "+ homme + "et la femme :" + femme + ", nombre des enfants :" + nbrEnfant ;
    }
    
}

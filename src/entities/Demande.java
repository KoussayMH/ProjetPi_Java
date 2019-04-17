/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;
import java.util.Objects;
import services.DemandeServices;

/**
 *
 * @author Lenovo
 */
public class Demande {
    private int id; 
    private Date dated; 
    private String etat ; 
    private int ressource ; 
    private int event; 
    
    public Demande()
    {
        
    }

    public Demande(int id, Date dated, String etat, int ressource, int event) {
        this.id = id;
        this.dated = dated;
        this.etat = etat;
        this.ressource = ressource;
        this.event = event;
    }
    public Demande(int event,int ressource,   Date dated ) {
        this.dated = dated;
        this.ressource = ressource;
        this.event = event;
        this.etat="EN COURS"; 
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getRessource() {
        return ressource;
    }

    public void setRessource(int ressource) {
        this.ressource = ressource;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }
DemandeServices demande = new DemandeServices(); 
    @Override
    public String toString() {
        return "Demande le " + dated + " pour l'evenement " + demande.chercherTitreEvenement(event) +  " à Mr/Mmd" + demande.chercherPrenNomRs(ressource) + " DEMANDE " + etat ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.dated);
        hash = 43 * hash + Objects.hashCode(this.etat);
        hash = 43 * hash + Objects.hashCode(this.ressource);
        hash = 43 * hash + Objects.hashCode(this.event);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Demande other = (Demande) obj;
        
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.dated, other.dated)) {
            return false;
        }
        if (!Objects.equals(this.ressource, other.ressource)) {
            return false;
        }
        if (!Objects.equals(this.event, other.event)) {
            return false;
        }
        return true;
    }
 
    
    
}

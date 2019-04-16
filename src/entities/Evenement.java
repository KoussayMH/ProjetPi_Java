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
public class Evenement {
    int id ; 
   
    Date dated; 
    
    public Evenement()
    {
        
    }

    public Evenement(int id, Date dated) {
        this.id = id;
      
        this.dated = dated;
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

    DemandeServices demande = new DemandeServices(); 
    @Override
    public String toString() {
        return "Evenement " + demande.chercherTitreEvenement(id)+ " le " + dated ;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.dated);
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
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.dated, other.dated)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}

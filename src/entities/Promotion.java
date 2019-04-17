/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Malek Ben Jemaa
 */
public class Promotion {
   private  SimpleIntegerProperty id ;
   private SimpleStringProperty Descrpiton;
   private  SimpleIntegerProperty duree ;
   private   LocalDate date_debut ;
   private   LocalDate date_fin ; 
   private SimpleIntegerProperty pourcentage ;
    
    public Promotion ()
    {
        
    }
    
    public Promotion( String Description, int duree ,LocalDate date_debut , LocalDate date_fin , int pourcentage)
    {
        this.Descrpiton = new SimpleStringProperty(Description); 
        this.duree = new SimpleIntegerProperty(duree);
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.pourcentage= new SimpleIntegerProperty(pourcentage);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
       this.id = new SimpleIntegerProperty(id);
    }

    public String getDescrpiton() {
        return Descrpiton.get();
    }

    public void setDescrpiton(String Descrpiton) {
        this.Descrpiton = new SimpleStringProperty (Descrpiton);
    }

    public int getDuree() {
        return duree.get();
    }

    public void setDuree( int duree) {
        this.duree = new SimpleIntegerProperty (duree);
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public int getPourcentage() {
        return pourcentage.get();
    }

    public void setPourcentage( int pourcentage) {
        this.pourcentage = new SimpleIntegerProperty(pourcentage);
    }
    
     public SimpleStringProperty getDescriptionProprety() {
        return Descrpiton;
    }
      public SimpleIntegerProperty getDureeProprety() {
        return duree;
    }
      public SimpleObjectProperty getDateDebutProprety(){
          return new SimpleObjectProperty<LocalDate>(date_debut); 
      }
      public SimpleObjectProperty getDateFinProprety(){
          return new SimpleObjectProperty<LocalDate>(date_fin); 
      }
       public SimpleIntegerProperty getPourcentageProprety() {
        return pourcentage;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.Descrpiton);
        hash = 31 * hash + Objects.hashCode(this.duree);
        hash = 31 * hash + Objects.hashCode(this.date_debut);
        hash = 31 * hash + Objects.hashCode(this.date_fin);
        hash = 31 * hash + Objects.hashCode(this.pourcentage);
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
        final Promotion other = (Promotion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.Descrpiton, other.Descrpiton)) {
            return false;
        }
        if (!Objects.equals(this.duree, other.duree)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        if (!Objects.equals(this.pourcentage, other.pourcentage)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", Descrpiton=" + Descrpiton + ", duree=" + duree + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", pourcentage=" + pourcentage + '}';
    }

    public void setDate(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    
}

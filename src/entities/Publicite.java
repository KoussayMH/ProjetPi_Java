/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Arrays;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Malek Ben Jemaa
 */
public class Publicite {
    
    private SimpleIntegerProperty  id ;
    private SimpleStringProperty description ;
    private SimpleStringProperty date;
    private SimpleStringProperty duree;
    //private SimpleStringProperty image;
     private SimpleStringProperty imageBLOB ; 
    
    private SimpleIntegerProperty priorite;
    private SimpleStringProperty event ; 
    
    
    public Publicite ()
    {
        
    }

    public Publicite(String description, String date, String duree, String imageBLOB ,int priorite) {
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleStringProperty(date);
        this.duree = new SimpleStringProperty(duree);
        this.imageBLOB = new  SimpleStringProperty(imageBLOB) ;
        //this.image = new SimpleStringProperty(image);
        this.priorite= new SimpleIntegerProperty(priorite);
    }
    public Publicite(String description, String date, String duree, String imageBLOB ,int priorite, String Event) {
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleStringProperty(date);
        this.duree = new SimpleStringProperty(duree);
        this.imageBLOB = new  SimpleStringProperty(imageBLOB) ;
        //this.image = new SimpleStringProperty(image);
        this.priorite= new SimpleIntegerProperty(priorite);
        this.event = new  SimpleStringProperty(Event); 
    }
  
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
       this.id = new SimpleIntegerProperty(id);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public String getEvent() {
        return event.get();
    }

    public void setEvent(String event) {
        this.event = new SimpleStringProperty(event);
    }
    
    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
       this.date = new SimpleStringProperty(date);
    }

    public String getDuree() {
        return duree.get();
    }

    public void setDuree(String duree) {
        this.duree = new SimpleStringProperty(duree);
    }

    public String getImageBlob() {
        return imageBLOB.get();
    }

    public void setImageBlob(String imageBLOB) {
        this.imageBLOB = new SimpleStringProperty(imageBLOB);
    }
    
    public int getPriorite() {
        return priorite.get();
    }
 public void setPriorite(int priorite) {
       this.priorite = new SimpleIntegerProperty(priorite);
    }
 
  public SimpleStringProperty getDescriptionProprety() {
        return description;
    }
 
  public SimpleStringProperty getDateProprety() {
        return date;
    }
 
  public SimpleStringProperty getDureeProprety() {
        return duree;
    }
  public SimpleIntegerProperty getPrioriteProprety() {
        return priorite;
    }
 public SimpleStringProperty getEventProprety() {
        return event;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.duree);
        hash = 97 * hash + Objects.hashCode(this.imageBLOB);
//        hash = 97 * hash + Arrays.hashCode(this.ImageByte);
        hash = 97 * hash + Objects.hashCode(this.priorite);
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
        final Publicite other = (Publicite) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.duree, other.duree)) {
            return false;
        }
        if (!Objects.equals(this.imageBLOB, other.imageBLOB)) {
            return false;
        }
/*        if (!Arrays.equals(this.ImageByte, other.ImageByte)) {
            return false;
        }*/
        if (!Objects.equals(this.priorite, other.priorite)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Publicite{" + "id=" + id + ", description=" + description + ", date=" + date + ", duree=" + duree + ", imageBLOB=" + imageBLOB+ ", priorite=" + priorite + '}';
    }

    
    
    
    
    
    
    
    
    
    
    
}

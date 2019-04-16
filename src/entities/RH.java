/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Arrays;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lenovo
 */
public class RH {
    private SimpleIntegerProperty id ; 
    private SimpleIntegerProperty nb  ; 
    private SimpleStringProperty adresse ; 
    private String imageBlob; 
    private byte[] ImageByte;
    private SimpleStringProperty nom; 
    private SimpleStringProperty prenom; 
    private SimpleStringProperty description ; 
    private SimpleStringProperty domaine ; 
    private SimpleStringProperty link; 
    private SimpleStringProperty company; 
    private SimpleStringProperty email; 
    
    public RH()
    {
        
    }
    public RH ( int id, String nom, String prenom, String description, String adresse, String domaine, String company, String email, String link , String imageBlob   ){
          
        this.id = new SimpleIntegerProperty(id) ; 
        this.nom=new SimpleStringProperty(nom); 
        this.prenom= new SimpleStringProperty(prenom); 
        this.description= new SimpleStringProperty(description); 
        this.adresse= new SimpleStringProperty(adresse); 
        this.domaine= new SimpleStringProperty(domaine); 
        this.company= new SimpleStringProperty(company); 
        this.email= new SimpleStringProperty(email); 
        this.link= new SimpleStringProperty(link); 
        this.imageBlob= imageBlob; 
 
    }
       public RH (  String nom, String prenom, String description, String adresse, String domaine, String company, String email, String link , String imageBlob   ){
          
        this.nom=new SimpleStringProperty(nom); 
        this.prenom= new SimpleStringProperty(prenom); 
        this.description= new SimpleStringProperty(description); 
        this.adresse= new SimpleStringProperty(adresse); 
        this.domaine= new SimpleStringProperty(domaine); 
        this.company= new SimpleStringProperty(company); 
        this.email= new SimpleStringProperty(email); 
        this.link= new SimpleStringProperty(link); 
        this.imageBlob= imageBlob; 
 
    }
    
     public byte[] getMyImage(){
        return ImageByte;
    }
      public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty (id);
    }
     public String getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(String imageBLOB) {
        this.imageBlob = imageBLOB ;
    }
    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom = new SimpleStringProperty(nom);
    }
     public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String prenom) {
        this.prenom = new SimpleStringProperty(prenom);
    }

      public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }
     public String getAdresse() {
        return adresse.get();
    }

    public void setAdresse (String adresse) {
        this.adresse = new SimpleStringProperty(adresse);
    }
       public String getDomaine() {
        return domaine.get();
    }

    public void setDomaine (String domaine) {
        this.domaine = new SimpleStringProperty(domaine);
    }
        public String getCompany() {
        return company.get();
    }

    public void setCompany (String company) {
        this.company = new SimpleStringProperty(company);
    }
           public String getEmail() {
        return email.get();
    }

    public void setEmail (String email) {
        this.email = new SimpleStringProperty(email);
    }
        public String getLink() {
        return link.get();
    }

    public void setLink (String link) {
        this.link = new SimpleStringProperty(link);
    }
    
    
    
    
    public SimpleStringProperty getNomProperty(){
        return nom;
    }
    public SimpleStringProperty getPrenomProperty(){
        return prenom;
    }
    public SimpleStringProperty getDomaineProperty(){
        return domaine;
    }
    public SimpleStringProperty getDescriptionProperty(){
        return description;
    }
    public SimpleStringProperty getCompanyProperty(){
        return company;
    }
    public SimpleStringProperty getAdresseProperty(){
        return adresse;
    }
    public SimpleStringProperty getEmailProperty(){
        return email;
    }
    public SimpleStringProperty getLinkProperty(){
        return link;
    }

    @Override
    public String toString() {
        return "RH{" + "id=" + id + ", nb=" + nb + ", adresse=" + adresse + ", imageBlob=" + imageBlob + ", ImageByte=" + ImageByte + ", nom=" + nom + ", prenom=" + prenom + ", description=" + description + ", domaine=" + domaine + ", link=" + link + ", company=" + company + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final RH other = (RH) obj;
        if (!Objects.equals(this.imageBlob, other.imageBlob)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nb, other.nb)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Arrays.equals(this.ImageByte, other.ImageByte)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.domaine, other.domaine)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
 
    
    
    
    
    
}

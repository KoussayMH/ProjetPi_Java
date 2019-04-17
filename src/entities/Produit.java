/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lenovo
 */
public class Produit {
    private SimpleIntegerProperty id ; 
    private SimpleStringProperty image ;    
    private SimpleStringProperty imageBLOB ; 
    private byte[] ImageByte;
    private SimpleFloatProperty prix ; 
    private SimpleStringProperty libelle ; 
    private SimpleIntegerProperty quantite ; 
    private SimpleStringProperty categorie ;     
    private SimpleStringProperty description     ;
 ; 
    public Produit()
    {
        
    }

public Produit(String libelle, byte[] ImageByte)
    {
        this.libelle=new SimpleStringProperty(libelle) ;
        this.ImageByte= ImageByte; 
    }
    
    public Produit (String libelle, float prix, int quantite, String categorie , String description, String imageBLOB )
    {
        this.libelle=new SimpleStringProperty(libelle) ; 
        this.prix= new SimpleFloatProperty(prix) ; 
        this.quantite = new  SimpleIntegerProperty(quantite) ;         
        this.categorie = new  SimpleStringProperty(categorie) ;      
        this.description = new  SimpleStringProperty(description) ;        
        this.imageBLOB = new  SimpleStringProperty(imageBLOB) ;

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

    public String getImage() {
        return image.get();
    }

    public void setImage(String  image) {
        this.image = new SimpleStringProperty(image);
    }
     public String getImageBlob() {
        return imageBLOB.get();
    }

    public void setImageBlob(String imageBLOB) {
        this.imageBLOB = new SimpleStringProperty(imageBLOB);
    }

    public float getPrix() {
        return prix.get();
    }

    public void setPrix( float prix) {
        this.prix = new SimpleFloatProperty(prix);
    }

    public String getLibelle() {
        return libelle.get();
    }

    public void setLibelle(String libelle) {
        this.libelle = new SimpleStringProperty(libelle);
    }

    public int getQuantite() {
        return quantite.get();
    }

    public void setQuantite(int quantite) {
        this.quantite = new SimpleIntegerProperty (quantite);
    }

    public String getCategorie() {
        return  categorie.get();
    }

    public void setCategorie(String categorie) {
        this.categorie = new SimpleStringProperty (categorie);
    }
    public String getDescription() {
        return  description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty (description);
    }
    
    public SimpleStringProperty getLibelleProperty(){
        return libelle;
    }
    public SimpleFloatProperty getPrixProperty(){
        return prix;
    }
    public SimpleIntegerProperty getQuantiteProperty(){
        return quantite;
    }
    public SimpleStringProperty getDescriptionProperty(){
        return description;
    }
    public SimpleStringProperty getCategorieProperty(){
        return categorie ;
    }

    @Override
    public String toString() {
        return "Libelle :" + this.getLibelle()+ "`\n Categorie :"+ this.getCategorie() + "\n Prix :" + this.getPrix()+ "\n";     
    }
    
    
    
    

   
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.image);
        hash = 97 * hash + Objects.hashCode(this.prix);
        hash = 97 * hash + Objects.hashCode(this.libelle);
        hash = 97 * hash + Objects.hashCode(this.quantite);
        hash = 97 * hash + Objects.hashCode(this.categorie);
        hash = 97 * hash + Objects.hashCode(this.description);
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
        final Produit other = (Produit) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.prix, other.prix)) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.quantite, other.quantite)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    
    

    
    
        
    
    
}

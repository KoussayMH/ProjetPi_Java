/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Produit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import utils.Connexion;
import utils.Utils;

/**
 *
 * @author Lenovo
 */
public class ProduitServices {
    
    
    private static ProduitServices instance;
    private Statement st;
    private ResultSet rs;
    
    public ProduitServices() {
        Connexion cs=Connexion.getInstance();
        try {
            st=cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ProduitServices getInstance(){
        if(instance==null) 
            instance=new ProduitServices();
        return instance;
    }
    public boolean verifierNombre(int nbre, int id){
        boolean test = false ; 
        int quantite=0; 
        String req = "select quantite from produit where id ='"+id+"'";
         try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
            quantite= rs.getInt("quantite");
             
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (quantite >= nbre)
         {
             test = true;
             
         }
         else 
         {
             test =false; 
         }
         System.out.println("QUANTITE BASE "+ quantite);
        System.out.println("QUANTITE DEMANDE "+ nbre);
        return test ; 
    }

    public  void insert (Produit o) throws FileNotFoundException
    {
         Connexion cs=Connexion.getInstance();
        FileInputStream input = null;
        System.out.println(o.getImageBlob());
        String requete = "INSERT INTO produit " + "(prix,libelle, description,quantite,categorie, imageblob) VALUES" + "(?,?,?,?,?,?)";
        try {

            PreparedStatement pst = cs.getConnection().prepareStatement(requete);
            pst.setFloat(1, o.getPrix());
            pst.setString(2, o.getLibelle());            
            pst.setString(3, o.getDescription());
            pst.setInt(4, o.getQuantite());
            pst.setString(5, o.getCategorie());
                                         
                        File theFile = new File(o.getImageBlob());
			input = new FileInputStream(theFile);
	    pst.setBinaryStream(6, input);
            pst.execute();
            Utils util = new Utils (); 
            utils.AlertMaker.showSimpleAlert("Ajout", "Le produit est ajouté avec succès");
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            System.out.println(ex.getMessage());
        }
        
    }
    
    public  InputStream displayImageById(int id)
    {
        InputStream is = null; 
            String req="select imageblob from produit where id ="+id;
           FileInputStream input = null;
            try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
            is= rs.getBinaryStream("imageblob");
             
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         return is;
    }
    
    public InputStream displayImageByIndex( int index ) {
           int count = 0; 
           InputStream is = null; 
           String req="select * from produit ";
           int test = 0; 
        try {
                 rs=st.executeQuery(req);
                 while(rs.next()){
                
                if (count == index )
                {
                    System.out.println("INDEX TROUVE"+ count);
                   is= rs.getBinaryStream("imageblob");
                  return is ; 
                   
              
                }
                else {
                    count ++; 
                }
                
            
               
                  }  
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       

        return is;
    }
         
    public void displayImages()
    {
        Connexion cs=Connexion.getInstance();
        try (
        Statement statement = cs.getConnection().createStatement(); 
        ResultSet resultSet = statement.executeQuery("select imageblob from produit")) {
            for (int i = 1; resultSet.next(); i++) {
                try (InputStream inputStream = resultSet.getBinaryStream("imageblob")) {
                    Path path = Paths.get("produitImages/produit" + i + ".jpg");
                    Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String donneesGalerie(int id)
    {
        String req="select * from produit where id ="+id;
           Produit p=new Produit();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt(1));
                p.setPrix(rs.getFloat(3));                
                p.setLibelle(rs.getString("libelle"));
                p.setDescription(rs.getString("description"));
                p.setQuantite(rs.getInt(3));
                p.setCategorie(rs.getString("categorie"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Libelle :" + p.getLibelle()+ "`\n Categorie :"+ p.getCategorie() + "\n Prix :" + p.getPrix()+ "\n"; 
    }
    
    public ObservableList<String> listViewProduit() {
        String donnees=""; 
        ObservableList<String> list=FXCollections.observableArrayList();       
        for (int i=0; i< this.displayAllList().size(); i++)
        {
            donnees = this.donneesGalerie(this.displayAllList().get(i).getId()); 
            list.add(donnees); 
            donnees=""; 
        }
        return list; 
        
    }
            
    public void delete(Produit o) {
        String req="delete from produit where id="+o.getId();
        Produit p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
            Utils util = new Utils (); 
            utils.AlertMaker.showSimpleAlert("Supression", "Le produit est supprimé avec succès");
             
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    public ObservableList<Produit> displayAll() {
        String req="select * from produit";
        ObservableList<Produit> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Produit p=new Produit();
                p.setId(rs.getInt(1));
                p.setPrix(rs.getFloat(3));                
                p.setLibelle(rs.getString("libelle"));
                p.setDescription(rs.getString("description"));
                p.setQuantite(rs.getInt(3));
                p.setCategorie(rs.getString("categorie"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Produit> displayAllList() {
        String req="select * from produit";
        List<Produit> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Produit p=new Produit();
                p.setId(rs.getInt(1));
                p.setPrix(rs.getFloat(3));                
                p.setLibelle(rs.getString("libelle"));
                p.setDescription(rs.getString("description"));
                p.setQuantite(rs.getInt(3));
                p.setCategorie(rs.getString("categorie"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Produit displayById(int id) {
           String req="select * from produit where id ="+id;
           Produit p=new Produit();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
               p.setId(rs.getInt(1));
                p.setPrix(rs.getFloat(3));                
                p.setLibelle(rs.getString("libelle"));
                p.setDescription(rs.getString("description"));
                p.setQuantite(rs.getInt(3));
                p.setCategorie(rs.getString("categorie"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    public boolean update(Produit p) {
        String qry = "UPDATE produit SET prix = '"+p.getPrix()+"', libelle= '"+p.getLibelle()+"', description= '"+p.getDescription()+"', "
                + "quantite= '"+p.getQuantite()+"', categorie= '"+p.getCategorie()+"' WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                System.out.println("produit ************  modifié ");
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 
    
    
    
    
    /******************************CATEGORIE******************************/
    public void updatecategorie(int nb, String nom ) {
        String qry = "UPDATE categorie SET quantite = quantite + '"+nb+"' Where nom like '"+nom+"'"; 
        
        try {
            if (st.executeUpdate(qry) > 0) {
                System.out.println("nombre ajouté a categorie ************   ");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public void updateQuantite(int nb, int idProduit ) {
        String qry = "UPDATE produit SET quantite = quantite - '"+nb+"' Where  id= '"+idProduit+"'"; 
        
        try {
            if (st.executeUpdate(qry) > 0) {
                System.out.println("QUANTITE ----------- ************   ");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public ObservableList<String> listeCategories()
    {
        String resultat =""; 
        ObservableList<String> liste=FXCollections.observableArrayList(); 
        String req="select * from categorie";
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                resultat = rs.getString("nom"); 
                liste.add(resultat); 
                resultat=""; 
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("LA LISTE DES CATEGORIES EST == "+ liste);
         return liste; 

        
    }
    
    public int nombreParCat(String nom)
    {
        int nombre =0; 
        String req="select quantite from categorie where nom like '"+nom+"'";
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                nombre = rs.getInt("quantite"); 
                System.out.println("QUANTITE == " + nombre);
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nombre; 
    }
    
    
  
 
    
}

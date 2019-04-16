/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.RH;
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
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class RHServices {
    
    
    private static RHServices instance;
    private Statement st;
    private ResultSet rs;
    
    public RHServices() {
        Connexion cs=Connexion.getInstance();
        try {
            st=cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(RHServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static RHServices getInstance(){
        if(instance==null) 
            instance=new RHServices();
        return instance;
    }

    public ObservableList<String> listViewRH() {
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
     public boolean dispoDateRs(int idRs, Date date)
 {
     boolean test= true;
     int count = 0; 
     String req = "Select * from event where ressource_id = '"+idRs+"' and date = '"+date+"' ";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                count ++; 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RHServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (count != 0)
        {
            test = false ; 
        }
     
     return test ; 
 }
    
    
    public  void insert (RH o) throws FileNotFoundException
    {
         Connexion cs=Connexion.getInstance();
        FileInputStream input = null;
        System.out.println(o.getImageBlob());
        String requete = "INSERT INTO ressource " + "(nom,prenom, description,adresse,domaine,company,email, link, imageblob) VALUES" + "(?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement pst = cs.getConnection().prepareStatement(requete);
            pst.setString(1, o.getNom());
            pst.setString(2, o.getPrenom());            
            pst.setString(3, o.getDescription());
            pst.setString(4, o.getAdresse());
            pst.setString(5, o.getDomaine());           
            pst.setString(6, o.getCompany());
            pst.setString(7, o.getEmail());
            pst.setString(8, o.getLink());
           
                        File theFile = new File(o.getImageBlob());
			input = new FileInputStream(theFile);
	    pst.setBinaryStream(9, input);
            pst.execute();
            System.out.println("RH Added");
             Utils util = new Utils (); 
            utils.AlertMaker.showSimpleAlert("Ajouter RH", "RH ajoué avec succèst");
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            System.out.println(ex.getMessage());
        }
        
    }
     public  InputStream displayImageById(int id)
    {
        InputStream is = null; 
            String req="select imageblob from ressource where id ="+id;
           FileInputStream input = null;
            try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
            is= rs.getBinaryStream("imageblob");
             
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(RHServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         return is;
    }
     
         public InputStream displayImageByIndex( int index ) {
           int count = 0; 
           InputStream is = null; 
           String req="select * from ressource ";
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
        ResultSet resultSet = statement.executeQuery("select imageblob from ressource")) {
            for (int i = 1; resultSet.next(); i++) {
                try (InputStream inputStream = resultSet.getBinaryStream("imageblob")) {
                    Path path = Paths.get("RHImages/RH" + i + ".jpg");
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
        String req="select * from ressource where id ="+id;
           RH p=new RH();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
               
                p.setNom(rs.getString("nom"));                
                p.setPrenom(rs.getString("prenom"));
                p.setDescription(rs.getString("description"));
                p.setDomaine(rs.getString("Domaine"));
                
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(RHServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Prénom :" + p.getPrenom()+ "`\n Nom :"+ p.getNom() + "\n Domaine :" + p.getDomaine()+ "\n Description :"+ p.getDescription(); 
    }
         
         
         
 public void delete(RH o) {
       String req="delete from ressource where id="+o.getId();
        RH p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
              Utils util = new Utils (); 
            utils.AlertMaker.showSimpleAlert("Supression", "RH est supprimé avec succèst");
        } catch (SQLException ex) {
            Logger.getLogger(RHServices.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
           
           
           
            public ObservableList<RH> displayAll() {
        String req="select * from ressource";
        ObservableList<RH> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                
                
                RH p=new RH();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));                
                p.setPrenom(rs.getString("prenom"));
                p.setDescription(rs.getString("description"));
                p.setAdresse(rs.getString("adresse"));
                p.setDomaine(rs.getString("domaine"));
                p.setCompany(rs.getString("company"));
                p.setEmail(rs.getString("email"));
                p.setLink(rs.getString("link"));
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RHServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
            
            public List<RH> displayAllList() {
        String req="select * from ressource";
        List<RH> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                RH p=new RH();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));                
                p.setPrenom(rs.getString("prenom"));
                p.setDescription(rs.getString("description"));
                p.setAdresse(rs.getString("adresse"));
                p.setDomaine(rs.getString("domaine"));
                p.setCompany(rs.getString("company"));
                p.setEmail(rs.getString("email"));
                p.setLink(rs.getString("link"));
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RHServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

public RH displayById(int id) {
           String req="select * from ressource where id ="+id;
           RH p=new RH();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));                
                p.setPrenom(rs.getString("prenom"));
                p.setDescription(rs.getString("description"));
                p.setAdresse(rs.getString("adresse"));
                p.setDomaine(rs.getString("categorie"));
                p.setCompany(rs.getString("company"));
                p.setEmail(rs.getString("email"));
                p.setLink(rs.getString("link"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(RHServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }     
                
 public boolean update(RH p) {
        String qry = "UPDATE ressource SET nom = '"+p.getNom()+"' , prenom = '"+p.getPrenom()+"' , description = '"+p.getDescription()+"' ,adresse= '"+p.getAdresse()+"'"
                + " ,domaine = '"+p.getDomaine()+"' ,company = '"+p.getCompany()+"' ,email = '"+p.getEmail()+"' , link = '"+p.getLink()+"' WHERE id = "+p.getId();
        try {
            if (st.executeUpdate(qry) > 0) {
                System.out.println("RH ************  modifié ");
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RHServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }




}

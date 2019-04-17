/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connex.Connexion;
import entities.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chaym
 */
public class PersonneServices {
    Connection cn= Connexion.getInstance().getConnection() ; 
  private  Statement st ; 
  private   PreparedStatement pst ; 
  private static TicketServices instance;
  private ResultSet rs;
    
    public void AjouterPersonne(Personne p)
    {
        String req="INSERT INTO personne(nom,prenom) VALUES('"+p.getNom()+"','"+p.getPrenom()+"')" ; 
        try { 
            st= cn.createStatement() ;
            st.executeUpdate(req) ; 
                   System.out.println("Personne Ajoutée "); 

        } catch (SQLException ex) {
            Logger.getLogger(PersonneServices.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }
    
    public void AjouterPersonne2(Personne p)
    {
        String req="INSERT INTO personne(nom,prenom) VALUES(?,?)" ; 
        try { 
            pst= cn.prepareStatement(req) ;
            pst.setString(1,p.getNom()); 
            pst.setString(2,p.getPrenom()); 
            pst.executeUpdate(); 
                               System.out.println("Personne 2 Ajoutée "); 



        } catch (SQLException ex) {
            Logger.getLogger(PersonneServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Personne> afficherPersonne()
    {
        List<Personne> myList= new ArrayList<>() ; 
        String req="SELECT * from personne";
        try { 
            st= cn.createStatement() ;
                     ResultSet rs =st.executeQuery(req) ; 
                     while(rs.next())
                     {
                         Personne p = new Personne() ; 
                        p.setId(rs.getInt(1)) ; 
                         p.setNom(rs.getString("nom"));
                         p.setNom(rs.getString("prenom"));
                         myList.add(p) ; 


                     }
                    

        } catch (SQLException ex) {
            Logger.getLogger(PersonneServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myList ; 
    }
    
}

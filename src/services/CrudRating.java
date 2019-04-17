/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import entites.RatingProduit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Connexion;

/**
 *
 * @author DELL
 */
public class CrudRating {
    
    private static CrudRating instance;
    private Statement st;
    private ResultSet rs;

    public void ajouterRating(RatingProduit r) {
        Connexion cs=Connexion.getInstance();
        try {
             st =  cs.getConnection().createStatement();
                    String req="insert into ratingProduit (id_client,id_produit,note) values ('"+r.getId_client()+"','"+r.getId_produit()+"','"+r.getNote()+"' )";

            st.execute(req);
        } catch (SQLException ex) {
            Logger.getLogger(CrudRating.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public float note(int id)
    {
        int nombre =0; 
        int somme = 0; 
        float resultat =0; 
        Connexion cs=Connexion.getInstance();
        String req = "Select * from ratingProduit where id_produit = '"+id+"' "; 
       try {
             st =  cs.getConnection().createStatement();
             ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {
               nombre ++ ; 
               somme = rs.getInt("note") + somme ; 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudRating.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("SOMMME == " + somme);
        System.out.println("NOMBRE ++ "+ nombre);
        
        try {
             if (nombre != 0)
        {
            resultat=  ((float ) somme / (float ) nombre) ; 
        }
        System.out.println("RESULTAT ++ "+ resultat);
        }catch(Exception e)
        {
            System.out.println("probleme de calcule fi note");
        }
       
       return (float ) resultat ; 
        
    }
    
    
    
    
    
        public ArrayList<RatingProduit> aaficherRating() {
                    Connexion cs=Connexion.getInstance();

        ArrayList<RatingProduit> l = new ArrayList<>();
        try {
             st =  cs.getConnection().createStatement();
            String req = "select * from ratingProduit";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {
                l.add(new RatingProduit(rs.getInt("id"),rs.getInt("id_produit"),rs.getInt("id_client"),rs.getInt("note")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudRating.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;

    }
        
        
        
        
        
        
        
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

//import entites.Produit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import services.ProduitServices;

/**
 *
 * @author chaym
 */
public class Connexion{
    private String url ="jdbc:mysql://localhost:3306/pidev" ; 
private String login="root"; 
private String pwd=""; 
private Connection cnx ; 
private static Connexion instance ; 

    private Connexion()      //change from public to private  
            
 {
    try { 
        cnx=DriverManager.getConnection(url,login, pwd);
       System.out.println("connexion établie"); 
    } catch (SQLException ex) {
        Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
    
    public Connection getConnection()
    {return cnx ; 
        
        
    }
    
    //puisque private => nouvelle methode
    
    public static Connexion getInstance()
            
    {
        if(instance==null)
        {
            instance= new Connexion();
        }
        return instance ; 
    }
/*
         public ArrayList<Produit> BindTable(){
        
   ArrayList<Produit> list = new ArrayList<Produit>();
     Connexion cs=Connexion.getInstance();
   Statement st;
   ResultSet rs;
   
   try {
   st = cs.getConnection().createStatement();
   rs = st.executeQuery("SELECT libelle, imageblob from produit`");
   
   Produit p;
   while(rs.next()){
   p = new Produit(
   rs.getString("libelle"),
   rs.getBytes("imageblob")
 
   );
   list.add(p);
   }
   
   } catch (SQLException ex) {
   Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
   }
   return list;
   }*/
}

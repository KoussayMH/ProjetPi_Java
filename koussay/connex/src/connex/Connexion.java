/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chaym
 */
public class Connexion {
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

    
}

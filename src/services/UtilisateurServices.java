/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import entites.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Connexion;
import utils.codeGen;

/**
 *
 * @author Lenovo
 */
public class UtilisateurServices {
    
    
    private static RHServices instance;
    private Statement st;
    private ResultSet rs;

    public void registerC(Utilisateur u) {
        Connexion cs=Connexion.getInstance();

        try {
       
        st = cs.getConnection().createStatement(); 
        String req = "INSERT INTO `utilisateur`(`username`, `email`, `password`, `active`, `date_registered`, `roles`, `confirmation_token`, `reset_token`) VALUES ('" + u.getUsername() + "','" + u.getEmail() + "','" + u.getPassword() + "','" + u.getActive() + "','" + u.getDate_reg() + "','" + u.getRole() + "','" + u.getConfirmation_token() + "','" + u.getReset_token() + "')";
        st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public boolean verifyPass(String username, String password) {
          Connexion cs=Connexion.getInstance();

        try {
             st = cs.getConnection().createStatement(); 
            String req = "SELECT password from utilisateur where utilisateur.username='" + username + "' ";
            ResultSet res = st.executeQuery(req);
            if (res.next()) {
                String passbd = res.getString(1);
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), passbd);
                if (result.verified) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
      public Utilisateur seConnecter(String login) {
                Connexion cs=Connexion.getInstance();
        try {

            st = cs.getConnection().createStatement();
            String req = "SELECT id,username,roles,email,active,confirmation_token from utilisateur where utilisateur.username='" + login + "' ";
            ResultSet res = st.executeQuery(req);
            if (res.next()) {
                String username = res.getString("username");
                int role = res.getInt("roles");
                String mail = res.getString("email");
                int active = res.getInt("active");
                int id = res.getInt("id");
                String token = res.getString("confirmation_token");
                Utilisateur u = new Utilisateur(id, active, role, username, mail, token);
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       public void confirmAccount(int id) {
            Connexion cs=Connexion.getInstance();
        try {
            PreparedStatement st = cs.getConnection().prepareStatement("UPDATE utilisateur SET active=1 WHERE id=?") ;
            st.setInt(1, id);
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
           public int checkUSername(String username) {
                           Connexion cs=Connexion.getInstance();

        try {
                st = cs.getConnection().createStatement(); 
            String req = "SELECT * from utilisateur where email='" + username + "' ";
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getString("username") != null) {
                    return res.getInt("id");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
           public String resetPasswordFirst(int id) {
            Connexion cs=Connexion.getInstance();

        try {
            codeGen code = new codeGen();
            String codeg = code.randomString(8);
            PreparedStatement st = cs.getConnection().prepareStatement("UPDATE utilisateur SET reset_token= ? WHERE id=?");
            st.setInt(2, id);
            st.setString(1, codeg);
            st.execute();
            return codeg;
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
               public void ResetPasswordLast(int id, String password) {
                               Connexion cs=Connexion.getInstance();

        try {
           PreparedStatement st = cs.getConnection().prepareStatement("UPDATE utilisateur SET password= ? WHERE id=?");
            st.setInt(2, id);
            st.setString(1, password);
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
               
           public boolean LoginFacebook(String username, String email) {
            Connexion cs=Connexion.getInstance();

        try {
             st = cs.getConnection().createStatement();
            String req = "SELECT count(id) from utilisateur where utilisateur.username='" + username + "' and utilisateur.email='" + email + "' ";
            ResultSet res = st.executeQuery(req);
            if (res.next()) {
                if (res.getInt(1) != 0) {
                    return true;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
            public void SignUpFacebook(String username, String email) {
            Connexion cs=Connexion.getInstance();

        try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
             st = cs.getConnection().createStatement();
            String req = " INSERT INTO utilisateur (username,email,password,active,date_registered,roles,confirmation_token,reset_token) VALUES ('" + username + "','" + email + "','Facebook',1,'" + date + "',0,'','') ";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
}

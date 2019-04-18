/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.MyDBcon;
import Entities.Club;
import Entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iheb
 */
public class ClubService {
    Connection cnx;

    public ClubService() throws SQLException {
         cnx = MyDBcon.getInstance().getCnx();
         
    }
    
    public int ajouterClub(Club c) {
         try {
             
             String req = "INSERT INTO `club`( `admin_id`, `nameClub`, `dateCreationClub`, `typeClub`, `statusClub`, `descriptionClub`,`photo_club`) VALUES (?,?,?,?,?,?,?)";
             PreparedStatement pstm = cnx.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
             pstm.setInt(1,c.getAdmin_id().getId());
             pstm.setString(2, c.getNameClub());
             pstm.setDate(3, Date.valueOf(LocalDate.now()));
             pstm.setString(4, c.getTypeClub());
             pstm.setString(5, c.getStatusClub());
             pstm.setString(6, c.getDescriptionClub());
             pstm.setString(7, c.getPhotoClub());
             
             pstm.executeUpdate();
             ResultSet result = pstm.getGeneratedKeys();
             if(result.next())return result.getInt(1);
         } catch (SQLException ex) {
             Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return -1;
    }
    
    public void modifierClub(Club c) {
         try {
             
             String req = "UPDATE `club` SET `admin_id`=? ,`nameClub`=? ,`dateCreationClub`=? ,`typeClub`=? ,`statusClub`=? ,`descriptionClub`=? ,`photo_club`=?  WHERE id=?";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1,c.getAdmin_id().getId());
             pstm.setString(2, c.getNameClub());
             pstm.setDate(3, Date.valueOf(LocalDate.now()));
             pstm.setString(4, c.getTypeClub());
             pstm.setString(5, c.getStatusClub());
             pstm.setString(6, c.getDescriptionClub());
             pstm.setString(7, c.getPhotoClub());
             pstm.setInt(8, c.getId());
             
             pstm.executeUpdate();
            
         } catch (SQLException ex) {
             Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    public void supprimerClub(int id) {
         try {
             
             String req = "DELETE FROM `club`   WHERE id=?";
             PreparedStatement pstm = cnx.prepareStatement(req);
             
             pstm.setInt(1, id);
             
             pstm.executeUpdate();
            
         } catch (SQLException ex) {
             Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    public Club getClubByid(int idc) throws SQLException {
         
             
             String req = "SELECT c.*,u.* FROM `club` c inner join User u on c.admin_id=u.id WHERE c.id= ? ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             ResultSet result = pstm.executeQuery();
             
             if(!result.next()) return null;
             //return (Adresse)result.getObject(1);
             
            int id = result.getInt("id");
            User admin_id = new User(result.getInt("admin_id"),result.getString("username") , result.getString("email"), result.getBoolean("enabled"), result.getString("firstName"), result.getString("lastName"), result.getString("descriptionUser"));
            String nameClub = result.getString("nameClub");
            java.util.Date dateCreationClub = result.getDate("dateCreationClub");
            String typeClub = result.getString("typeClub");
            String statusClub = result.getString("statusClub");
            String descriptionClub = result.getString("descriptionClub");
            String photoClub = result.getString("photo_Club");
             
             
            return new Club(id, admin_id, nameClub, dateCreationClub, typeClub, statusClub, descriptionClub, photoClub);
         
        
    }
    
    public List<Club> getAllClubs() throws SQLException {
         
             List<Club> lc = new ArrayList<>();
             String req = "SELECT c.*,u.* FROM `club` c inner join User u on c.admin_id=u.id  ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             
             ResultSet result = pstm.executeQuery();
             
             while(result.next()){
                int id = result.getInt("id");
                User admin_id = new User(result.getInt("admin_id"),result.getString("username") , result.getString("email"), result.getBoolean("enabled"), result.getString("firstName"), result.getString("lastName"), result.getString("descriptionUser"));
                String nameClub = result.getString("nameClub");
                java.util.Date dateCreationClub = result.getDate("dateCreationClub");
                String typeClub = result.getString("typeClub");
                String statusClub = result.getString("statusClub");
                String descriptionClub = result.getString("descriptionClub");
                String photoClub = result.getString("photo_Club");
             
             
                lc.add(new Club(id, admin_id, nameClub, dateCreationClub, typeClub, statusClub, descriptionClub, photoClub));
             }
             
             return lc;
        
    }
    public List<Club> getAllClubsByStat(String stat) throws SQLException {
         
             List<Club> lc = new ArrayList<>();
             String req = "SELECT c.*,u.* FROM `club` c inner join User u on c.admin_id=u.id where statusClub=? ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setString(1, stat);
             ResultSet result = pstm.executeQuery();
             
             while(result.next()){
                int id = result.getInt("id");
                User admin_id = new User(result.getInt("admin_id"),result.getString("username") , result.getString("email"), result.getBoolean("enabled"), result.getString("firstName"), result.getString("lastName"), result.getString("descriptionUser"));
                String nameClub = result.getString("nameClub");
                java.util.Date dateCreationClub = result.getDate("dateCreationClub");
                String typeClub = result.getString("typeClub");
                String statusClub = result.getString("statusClub");
                String descriptionClub = result.getString("descriptionClub");
                String photoClub = result.getString("photo_Club");
             
             
                lc.add(new Club(id, admin_id, nameClub, dateCreationClub, typeClub, statusClub, descriptionClub, photoClub));
             }
             
             return lc;
        
    }
    public List<Club> getAllClubsByAdmin(int ida) throws SQLException {
         
             List<Club> lc = new ArrayList<>();
             String req = "SELECT c.*,u.* FROM `club` c inner join User u on c.admin_id=u.id where admin_id =? ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, ida);
             ResultSet result = pstm.executeQuery();
             
             while(result.next()){
                int id = result.getInt("id");
                User admin_id = new User(result.getInt("admin_id"),result.getString("username") , result.getString("email"), result.getBoolean("enabled"), result.getString("firstName"), result.getString("lastName"), result.getString("descriptionUser"));
                String nameClub = result.getString("nameClub");
                java.util.Date dateCreationClub = result.getDate("dateCreationClub");
                String typeClub = result.getString("typeClub");
                String statusClub = result.getString("statusClub");
                String descriptionClub = result.getString("descriptionClub");
                String photoClub = result.getString("photo_Club");
             
             
                lc.add(new Club(id, admin_id, nameClub, dateCreationClub, typeClub, statusClub, descriptionClub, photoClub));
             }
             
             return lc;
        
    }
    public List<Club> getAllClubsByUser(int ida) throws SQLException {
         
             List<Club> lc = new ArrayList<>();
             String req = "SELECT c.*,u.* FROM `clubs_users` cu inner join Club c on c.id=cu.club_id inner join User u on c.admin_id=u.id where cu.user_id=?";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, ida);
             ResultSet result = pstm.executeQuery();
             
             while(result.next()){
                int id = result.getInt("id");
                User admin_id = new User(result.getInt("admin_id"),result.getString("username") , result.getString("email"), result.getBoolean("enabled"), result.getString("firstName"), result.getString("lastName"), result.getString("descriptionUser"));
                String nameClub = result.getString("nameClub");
                java.util.Date dateCreationClub = result.getDate("dateCreationClub");
                String typeClub = result.getString("typeClub");
                String statusClub = result.getString("statusClub");
                String descriptionClub = result.getString("descriptionClub");
                String photoClub = result.getString("photo_Club");
             
             
                lc.add(new Club(id, admin_id, nameClub, dateCreationClub, typeClub, statusClub, descriptionClub, photoClub));
             }
             
             return lc;
        
    }
    
    public List<User> getAllClubsMembreByID(int idc) throws SQLException {
         
             List<User> lc = new ArrayList<>();
             String req = "SELECT c.*,u.* FROM `clubs_users` c inner join User u on c.user_id=u.id where club_id=? ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             ResultSet result = pstm.executeQuery();
             
             while(result.next()){
                
                User user = new User(result.getInt("id"),result.getString("username") , result.getString("email"), result.getBoolean("enabled"), result.getString("firstName"), result.getString("lastName"), result.getString("descriptionUser"));
                
             
             
                lc.add(user);
             }
             
             return lc;
        
    }
    
     public List<User> getAllClubsAttenteMembreByID(int idc) throws SQLException {
         
             List<User> lc = new ArrayList<>();
             String req = "SELECT u.* FROM `demande` d inner join User u on d.user_id=u.id where club_id=? and etat=0 ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             ResultSet result = pstm.executeQuery();
             
             while(result.next()){
                
                User user = new User(result.getInt("id"),result.getString("username") , result.getString("email"), result.getBoolean("enabled"), result.getString("firstName"), result.getString("lastName"), result.getString("descriptionUser"));
                
             
             
                lc.add(user);
             }
             
             return lc;
        
    }
    
    public void AccepterClub(int idc) {
         try {
             
             String req = "UPDATE `club` SET `statusClub`=?   WHERE id=?";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setString(1, "Approved");
             pstm.setInt(2, idc);
             pstm.executeUpdate();
             
             req = "Select admin_id from `club` WHERE id=?";
             PreparedStatement pstm2 = cnx.prepareStatement(req);
             pstm2.setInt(1, idc);
             ResultSet res = pstm2.executeQuery();
             res.next();
             int idu = res.getInt(1);
             
             req = "INSERT INTO `clubs_users`(`club_id`, `user_id`) VALUES (?,?)";
             pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             pstm.setInt(2, idu);
             pstm.executeUpdate();
            
         } catch (SQLException ex) {
             Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    public void refuserClub(int idc) {
         try {
             
             String req = "UPDATE `club` SET `statusClub`=?   WHERE id=?";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setString(1, "Refused");
             pstm.setInt(2, idc);
             
             pstm.executeUpdate();
            
         } catch (SQLException ex) {
             Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    public int getAllClubsMembresNB(int idc) throws SQLException {
         
             
             String req = "SELECT COUNT(*) FROM `clubs_users` c where club_id=? ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             ResultSet result = pstm.executeQuery();
             
             while(result.next()){
                
              return result.getInt(1);
             }
             
             return 0;
        
    }
    public boolean checksMembresInClub(int idc,int idu) throws SQLException {
         
             
             String req = "Select * from clubs_users where club_id=? and user_id=? ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             pstm.setInt(2, idu);
             ResultSet result = pstm.executeQuery();
             
             while(result.next()){
                
              return true;
             }
             
             return false;
        
    }
    public void joinClub(int idc,int idu) throws SQLException {
         
             
             String req = "INSERT INTO `clubs_users`(`club_id`, `user_id`) VALUES (?,?)";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             pstm.setInt(2, idu);
             pstm.executeUpdate();
             String req1 = "DELETE from demande WHERE `club_id`=? and `user_id`=?";
             PreparedStatement pstm1 = cnx.prepareStatement(req1);
             pstm1.setInt(1, idc);
             pstm1.setInt(2, idu);
             pstm1.executeUpdate();

    }
    public void leaveClub(int idc,int idu) throws SQLException {

             String req = "DELETE from clubs_users WHERE `club_id`= ? and `user_id`= ?";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             pstm.setInt(2, idu);
             pstm.executeUpdate();

    }
    
    public int checksMembresTojoin(int idc,int idu) throws SQLException {

             String req = "Select etat from demande where club_id=? and user_id=? ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             pstm.setInt(2, idu);
             ResultSet result = pstm.executeQuery();
             
             while(result.next()){
                
              return result.getInt(1);
             }
             return -1;
    }
     public void DemandtojoinClub(int idc,int idu) throws SQLException {
         
             
             String req = "INSERT INTO `demande`(`club_id`, `user_id`) VALUES (?,?)";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             pstm.setInt(2, idu);
             pstm.executeUpdate();

    }
     public void refusetojoinClub(int idc,int idu) throws SQLException {
         
             
             String req = "UPDATE `demande` SET `etat`= 1 WHERE `club_id`=? and`user_id`=?";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             pstm.setInt(2, idu);
             pstm.executeUpdate();

    }
     public void ajouterListNoire(int idc,int idu) throws SQLException {
         
             
             String req = "INSERT INTO `demande`(`club_id`, `user_id`,etat) VALUES (?,?,1)";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             pstm.setInt(2, idu);
             pstm.executeUpdate();

    }
    
}

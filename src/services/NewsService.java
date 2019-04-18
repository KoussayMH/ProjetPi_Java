/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.MyDBcon;
import Entities.Club;
import Entities.News;
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
public class NewsService {
    Connection cnx;

    public NewsService() throws SQLException {
         cnx = MyDBcon.getInstance().getCnx();
         
    }
    
    public int ajouterNews(News n) {
         try {
             
             String req = "INSERT INTO `news`(`id_club`, `title`, `description`, `creationDate`, `photo_news`) VALUES (?,?,?,?,?)";
             PreparedStatement pstm = cnx.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
             pstm.setInt(1,n.getIdClub().getId());
             pstm.setString(2, n.getTitle());
             pstm.setString(3, n.getDescription());
             pstm.setDate(4, (Date) n.getCreationDate());
             pstm.setString(5, n.getPhoto_news());

             pstm.executeUpdate();
             ResultSet result = pstm.getGeneratedKeys();
             if(result.next())return result.getInt(1);
         } catch (SQLException ex) {
             Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return -1;
    }
    public void modifierNews(News n) {
         try {
             
             String req = "UPDATE `news` SET `id_club`=?,`title`=?,`description`=?,`creationDate`=?,`photo_news`=? WHERE `id`=?";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1,n.getIdClub().getId());
             pstm.setString(2, n.getTitle());
             pstm.setString(3, n.getDescription());
             pstm.setDate(4, (Date) n.getCreationDate());
             pstm.setString(5, n.getPhoto_news());
             pstm.setInt(6,n.getId());

             pstm.executeUpdate();
             
             
         } catch (SQLException ex) {
             Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }
    public void supprimerNews(int idn) {
         try {
             
             String req = "DELETE FROM `news` WHERE `id`=?";
             PreparedStatement pstm = cnx.prepareStatement(req);
             
             pstm.setInt(1,idn);

             pstm.executeUpdate();
            
             
         } catch (SQLException ex) {
             Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }
    
    public News getNewsByid(int idn) throws SQLException {
         
             ClubService cs = new ClubService();
             String req = "SELECT * FROM `news` WHERE id=? ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idn);
             ResultSet result = pstm.executeQuery();
             
             if(!result.next()) return null;
             //return (Adresse)result.getObject(1);
             
            int id = result.getInt("id");
            Club club = cs.getClubByid(result.getInt(2));
            String titre = result.getString("title");
            Date date = result.getDate("creationDate");
            String description = result.getString("description");
            String photoClub = result.getString("photo_news");
             
             
            return new News(id, club, titre, description, date, photoClub);
    }
    
    public List<News> getAllNews() throws SQLException {
            List<News> ns = new ArrayList<>();
             ClubService cs = new ClubService();
             String req = "SELECT * FROM `news` where creationDate <= SYSDATE() order by creationDate desc";
             PreparedStatement pstm = cnx.prepareStatement(req);
             ResultSet result = pstm.executeQuery();
             while (result.next()) {            
            int id = result.getInt("id");
            Club club = cs.getClubByid(result.getInt(2));
            String titre = result.getString("title");
            Date date = result.getDate("creationDate");
            String description = result.getString("description");
            String photoClub = result.getString("photo_news");

            ns.add(new News(id, club, titre, description, date, photoClub)) ;
        }
            return ns;
             
            
    }
    public List<News> getAllNewsByClub(int idc) throws SQLException {
            List<News> ns = new ArrayList<>();
             ClubService cs = new ClubService();
             String req = "SELECT * FROM `news` where `id_club`= ? order by creationDate desc";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idc);
             ResultSet result = pstm.executeQuery();
             while (result.next()) {            
                int id = result.getInt("id");
                Club club = cs.getClubByid(result.getInt(2));
                String titre = result.getString("title");
                Date date = result.getDate("creationDate");
                String description = result.getString("description");
                String photoClub = result.getString("photo_news");

                ns.add(new News(id, club, titre, description, date, photoClub)) ;
            }
            return ns;
            
    }
    
    
}

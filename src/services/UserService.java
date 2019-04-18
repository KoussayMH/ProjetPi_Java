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
public class UserService {
     Connection cnx;

    public UserService() throws SQLException {
         cnx = MyDBcon.getInstance().getCnx();
         
    }
    
    public User getUserById(int idu) throws SQLException{
        
             String req = "SELECT * FROM `user`  WHERE id = ? ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idu);
             ResultSet result = pstm.executeQuery();
             result.next();
             int id = result.getInt(1);
             String username = result.getString(2);
             String email = result.getString(4);
             boolean enabled = result.getBoolean(6);
             String password = result.getString(8);
             String firstName = result.getString(13);
             String lastName = result.getString(14);
             String descriptionUser = result.getString(15);
             return new User(id, username, email, enabled, firstName, lastName, descriptionUser);
        
    }
    
   /*
    public Club getUserByid(int idu) throws SQLException {
         
             
             String req = "SELECT c.*,u.* FROM `club` c inner join User u on c.admin_id=u.id WHERE c.id= ? ";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, idu);
             ResultSet result = pstm.executeQuery();
             
             if(!result.next()) return null;
             //return (Adresse)result.getObject(1);
             
            int id = result.getInt("id");
            //User admin_id = result.getString("");
            String nameClub = result.getString("nameClub");
            java.util.Date dateCreationClub = result.getDate("dateCreationClub");
            String typeClub = result.getString("typeClub");
            String statusClub = result.getString("statusClub");
            String descriptionClub = result.getString("descriptionClub");
            String photoClub = result.getString("photoClub");
             
             
            return new Club(id, admin_id, nameClub, dateCreationClub, typeClub, statusClub, descriptionClub, photoClub);
         
        
    }
    
   */
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DB.MyDBcon;
import Services.ClubService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iheb
 */
public class ActuTask extends TimerTask{
    Connection cnx;

    public ActuTask() throws SQLException {
        cnx = MyDBcon.getInstance().getCnx();
    }
    
    
    @Override
    public void run() {
         try {
             System.out.println("all old news deleted");
             String req = "DELETE FROM `news`   WHERE creationDate < ?";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setDate(1,Date.valueOf( LocalDate.now().minusDays(5)));
             pstm.executeUpdate();
            
         } catch (SQLException ex) {
             Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}

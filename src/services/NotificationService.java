/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author iheb
 */
public class NotificationService {
    Connection cnx;

    public NotificationService() throws SQLException {
         cnx = MyDBcon.getInstance().getCnx();
         
    }
     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.teknikindustries.bulksms.SMS;
import entities.Publicite;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.Connexion;

/**
 *
 * @author Malek Ben Jemaa
 */
public class PubliciteServices {
    
    private static PubliciteServices instance;
    private Statement st;
    private ResultSet rs;
    
    
    public PubliciteServices() {
        Connexion cs=Connexion.getInstance();
        try {
            st=cs.getConnexion().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static PubliciteServices getInstance(){
        if(instance==null) 
            instance=new PubliciteServices();
        return instance;
    }

    
    public void insert(Publicite o) 
    {        System.out.println("NOM DE L'evement ************ " + o.getEvent());

         Connexion cs=Connexion.getInstance();
         FileInputStream input = null;
        String req="insert into publicite (description,date,duree,priorite,event,imageBLOB ) values (?,?,?,?,?,?)";
        try {
            

            PreparedStatement pst = cs.getConnexion().prepareStatement(req);
            pst.setString(1, o.getDescription());
            pst.setString(2, o.getDate());            
            pst.setString(3, o.getDuree());
            pst.setInt(4, o.getPriorite());
             pst.setString(5, o.getEvent());    
            System.out.println("NOM DE L'evement ************ "+ o.getEvent());
                        File theFile = new File(o.getImageBlob());
        try {
            input = new FileInputStream(theFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PubliciteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
               pst.setBinaryStream(6, input); 
               
              
          
	    
            


            pst.execute();
            System.out.println("Added");
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            System.out.println(ex.getMessage());
        }
    }   

    
    public void delete(Publicite o) {
        String req="delete from publicite where id="+o.getId();
        Publicite p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteServices.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    
    public ObservableList<Publicite> displayAll() {
        String req= " select * from publicite ";
        ObservableList<Publicite> list= FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Publicite p=new Publicite();
                p.setId(rs.getInt(1));
                p.setDescription(rs.getString("description"));
                p.setDate(rs.getString("date"));
                p.setDuree(rs.getString("duree"));
                p.setImageBlob(rs.getString("imageblob"));
                p.setPriorite(rs.getInt(3));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Publicite> displayAllList() {
        String req="select * from publicite";
        List<Publicite> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Publicite p=new Publicite();
                p.setId(rs.getInt(1));
                p.setDescription(rs.getString("description"));
                p.setDate(rs.getString("date"));
                p.setDuree(rs.getString("duree"));
                p.setImageBlob(rs.getString("imageblob"));
               p.setPriorite(rs.getInt(3));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Publicite displayById(int id) {
           String req="select * from publicite where id ="+id;
           Publicite p=new Publicite();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setDate(rs.getString("date"));
                p.setDuree(rs.getString("duree"));
               // p.setImageBlob(rs.getString(""));
                p.setPriorite(rs.getInt("priorite"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    
    public boolean update(Publicite p) {
        String qry = "UPDATE publicite SET description = '"+p.getDescription()+"', date = '"+p.getDate()+"' , duree = '"+p.getDuree()+"', image = '"+p.getImageBlob()+"', priorite = '"+p.getPriorite()+"' WHERE id = "+p.getId();
        System.out.println(p);
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

       public  InputStream displayImageById(int id)
    {
        InputStream is = null; 
            String req="select imageblob from publicite where id ="+id;
           FileInputStream input = null;
            try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
            is= rs.getBinaryStream("imageblob");
             
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         return is;
    }
  
    public void displayImages()
    {
        Connexion cs=Connexion.getInstance();
        try (
        Statement statement = cs.getConnexion().createStatement(); 
        ResultSet resultSet = statement.executeQuery("select imageblob from produit")) {
    for (int i = 1; resultSet.next(); i++) {
        try (InputStream inputStream = resultSet.getBinaryStream("imageblob")) {
            Path path = Paths.get("produitImages/produit" + i + ".jpg");
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  public ObservableList<String> displayAllEvent() {
        String req= " select titre from event ";
        ObservableList<String> list= FXCollections.observableArrayList();       
        String resultat =""; 
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                resultat = rs.getString("titre"); 
                list.add(resultat);
                resultat =""; 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
  
public InputStream displayImageByIndex( int index ) {
    System.out.println("DIS¨PLAY IMAGE PAR INDEX");
           int count = 0; 
           InputStream is = null; 
           String req="select * from publicite ";
           int test = 0; 
             try {
                 rs=st.executeQuery(req);
                 while(rs.next()){
                
                if (count == index )
                {
                    System.out.println("INDEX TROUVE"+ count);
                   is= rs.getBinaryStream("imageblob");
                  return is ; 

                }
                else {
                    count ++; 
                }

                  }  
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       

        return is;
    }
  public ObservableList<String> listViewPub() {
        String donnees=""; 
        ObservableList<String> list=FXCollections.observableArrayList();       
        for (int i=0; i< this.displayAllList().size(); i++)
        {
            donnees = this.displayById(this.displayAllList().get(i).getId()).toString(); 
            list.add(donnees); 
            donnees=""; 
        }
        return list; 
        
    }
  
      
     public void envoyerMail(String mailDest, String NomEvent)
 {
      try{
            String host ="smtp.gmail.com" ;
            String user = "dorra.benabid@esprit.tn";
            String pass = "Dd123456789";
            String to = mailDest;
            String from = "dorra.benabid@esprit.tn";
            String subject = "Evénement à ne pas manquer ";
            String messageText = "Cher Mmd/Ms, \n ne ratez pas cet événement "+ NomEvent +"  \n Cordialement ";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
 }
 
     public void envoyerMailUtilisateur (String nomEvent)
     {
         String req = "Select * from utilisateur "; 
         String email =""; 
                 
           try {
            rs=st.executeQuery(req);
            while(rs.next()){
                email = rs.getString("email"); 
                envoyerMail(email, nomEvent );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    public void envoyezSms () 
    {
        SMS smsTut = new SMS (); 
        smsTut.SendSMS("matoussi", "oumaima07", "heey", "+21623063968", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
       
    }
    
    
    
    
    
    
}

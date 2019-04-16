/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Demande;
import entites.Evenement;
import entites.RH;

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
 * @author LenovoF
 * 
 */
public class DemandeServices {
    private static DemandeServices instance;
    private Statement st;
    private ResultSet rs;
    
    public DemandeServices() {
        Connexion cs=Connexion.getInstance();
        try {
            st=cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(RHServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DemandeServices getInstance(){
        if(instance==null) 
            instance=new DemandeServices();
        return instance;
    }
    
    public void ajouterDemande(int EventId, int RsId,  Date D)
    {        String req="insert into demande (event_id,DateD,etat, Ressource) values ('"+EventId+"','"+D+"','EN COURS', '"+RsId+"' )";
                try {
                            st.executeUpdate(req);
                            System.out.println("Demande ajoute ");
                     } catch (SQLException ex) {
                            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
    }
    
     public ObservableList<Evenement> chercherEvenementSansRS( )
    {
        String req = "select id, date from event where ressource_id is null "; 
        ObservableList<Evenement> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                
                
                Evenement p =new Evenement();
                p.setId(rs.getInt(1));
                p.setDated(rs.getDate("date"));                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
}
    
     
    public ObservableList<Demande> displayAll() {
        String req="select * from demande";
        ObservableList<Demande> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Demande p=new Demande();
                p.setId(rs.getInt(1));
                p.setDated(rs.getDate("dated"));                
                p.setEtat(rs.getString("etat"));
                p.setRessource(rs.getInt(5));
                p.setEvent(rs.getInt(2));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void modifierEtat(int idD, int idRs, int idEvent)
    {
        String req = "update demande set etat= 'Acceptée' where id = '"+idD+"' "; 
        try {
                            st.executeUpdate(req);
                            System.out.println("Demande Accepté ");
                     } catch (SQLException ex) {
                            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
        String req2 = "update event set ressource_id = '"+idRs+"' where id = '"+idEvent+"'" ; 
          try {
                            st.executeUpdate(req2);
                            System.out.println("Event modifie ");
                     } catch (SQLException ex) {
                            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
    }
    
  
    public void delete(int id) {
        String req="delete from demande where id="+id;
            try {
                     st.executeUpdate(req);
                     System.out.println("Demande supprimée ");
                } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        
     }
    
    }
    
    
    
     public List<Evenement> displayWithoutRSList() {
        String req="select * from event where ressource_id is null";
        List<Evenement> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Evenement p=new Evenement();
                p.setId(rs.getInt(1));
                p.setDated(rs.getDate("date"));                
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
 
    public ObservableList<String> listViewEvent(int idRs) {
       ObservableList<String> list = FXCollections.observableArrayList();   
       String req="select * from event where ressource_id ="+ idRs;
       Evenement p=new Evenement();
       String resultat =""; 
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
           
              
                p.setId(rs.getInt(1));
                p.setDated(rs.getDate("date")); 
                resultat = "ID EVENT  : " + p.getId()+ "`\n DATE  :"+ p.getDated(); 
                list.add(resultat); 
                
                resultat=""; 
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (list.isEmpty())
        {
            System.out.println("LISTE VIDE");
            resultat="aucun evenement disponible";
            list.add(resultat); 
        }
        return list ; 
        
    }
    
      public List<Demande> displayAllList() {
        String req="select * from Demande";
        List<Demande> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                 Demande p=new Demande();
                p.setId(rs.getInt(1));
                p.setDated(rs.getDate("dated"));                
                p.setEtat(rs.getString("etat"));
                p.setRessource(rs.getInt(5));
                p.setEvent(rs.getInt(2));
                list.add(p);
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean verifierExistance(Demande demande)
    {
        System.out.println("******************DEMANDE VERIF VERIF   = " + demande);
        boolean test = false ; 
        List<Demande> list=new ArrayList<>();
        int count =0; 
        list = this.displayAllList(); 
        System.out.println("******************List VERIF VERIF   = \n " + list);
        for (Demande d : list)
        {
            count ++; 
            System.out.println("count   =" + count);
            if (d.equals(demande))
            {
                test = true; 
            }
            
        }
        
        System.out.println("******************RESULTAT VERIF   = " + test);
        
        return test ; 
    }
    
    
    public int nombreEnCours() {
        String req="select * from Demande where etat like 'EN COURS' ";
        int count =0; 
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                count++; 
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    } 
    
    
     public void envoyerMail(String mailDest, String NomEvent, Date date)
 {
      try{
            String host ="smtp.gmail.com" ;
            String user = "dorra.benabid@esprit.tn";
            String pass = "Dd123456789";
            String to = mailDest;
            String from = "dorra.benabid@esprit.tn";
            String subject = "Nouvel evenement";
            String messageText = "Cher Mmd/Ms, \n On vous informe que vous aurez une nouvel evenement "+ NomEvent +" le " + date + "\n Cordialement ";
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
 
     
    
     
    public String chercherTitreEvenement(int id )
    {
        String req = "select * from event where id =  '"+id+"' "; 
        String nom =""; 
        try {
             
            rs=st.executeQuery(req);
            rs.next();
            nom = rs.getString("titre"); 
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nom; 
    }
    public String chercherMailRs(int id)
    {
        
      
        
        System.out.println("indiiiice + " +id);
         String req = "select * from ressource where id =  '"+id+"' "; 
        String email =""; 
        try {
             rs=st.executeQuery(req);
            rs.next(); 
             
               
            
           
            email = rs.getString("email"); 
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return email;
        
    }
     public String chercherPrenNomRs(int id)
    {
        System.out.println("indiiiice + " +id);
         String req = "select * from ressource where id =  '"+id+"' "; 
        String Resultat  =""; 
        try {
             rs=st.executeQuery(req);
            rs.next(); 
             
               
            
           
            Resultat = rs.getString("prenom") + " " + rs.getString("nom"); 
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Resultat;
        
    }
    /*
    public void rechercheDemande(Date dated)
    {
        String req = "select * from demande where dated = '"+dated+"'" ; 
        try {
            rs=st.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void accepterDemande(int id)
    {
        String req = "update demande set etat ='Accepte' where id= '"+id+"' "; 
           try {
            rs=st.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    public void ajouterRsEvent(int idRs, int idEvent)
    {
        String req = "update evenement set ressource_id ='"+idRs+"' where id = '"+idEvent+"'  "; 
        try {
            rs=st.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
    public void MiseAJourNote(int idRs)
    {
        String req= " update ressource set nb = nb + 1 where id = '"+idRs+"'   "; 
        try {
            rs=st.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    */
    
}

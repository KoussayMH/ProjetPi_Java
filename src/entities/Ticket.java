/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 *
 * @author chaym
 */
public class Ticket {
    private int id ; 
     private SimpleFloatProperty prix ;  
    private SimpleIntegerProperty qte ; 
    private Club club ; 
    private Event event ; 
    private String type ; 
    private String titre; 
    private String desc ; 
    private User user ; 

    public Ticket() {
    }

    public Ticket(float prix, int qte, Club club, Event event, String type, String titre, String desc) {
        this.prix = new SimpleFloatProperty(prix) ;
        this.qte = new SimpleIntegerProperty(qte) ;
        this.club = club;
        this.event = event;
        this.type = type;
        this.titre = titre;
        this.desc = desc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix.get();
    }

    public void setPrix(float prix) {
        this.prix =new SimpleFloatProperty(prix) ;
    }

    public int getQte() {
        return qte.get();
    }

    public void setQte(int qte) {
        this.qte = new SimpleIntegerProperty(qte) ;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Event getEvent() {
        return event;
    }
    
    public String getEv()
    {
        return event.getTitre() ; 
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
     public SimpleFloatProperty getPrixProperty(){
        return prix;
    }
    public SimpleIntegerProperty getQuantiteProperty(){
        return qte;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", prix=" + prix + ", qte=" + qte + ", club=" + club + ", event=" + event + ", type=" + type + ", titre=" + titre + ", desc=" + desc + ", user=" + user + '}';
    }

    
    
}

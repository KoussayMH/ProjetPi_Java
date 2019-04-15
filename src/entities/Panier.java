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
public class Panier {
    
    
    private int id ; 
    private Ticket ticket;
    private User user ;
    private String Type ;
    private String event ; 
    private SimpleFloatProperty prix ; 
    private User club ; 
    private SimpleIntegerProperty qte ; 
    private String etat="panier" ; 
    private Produit produit ; 

    
    
     public Panier() {
         etat="panier";
    }
    
    public Panier(int id, Ticket ticket, User club) {
        this.id = id;
        this.ticket = ticket;
        this.club = club;
    }

    public Panier(Ticket ticket, User club) {
        this.ticket = ticket;
        this.club = club;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getType() {
        return Type;
    }

    public Float getPrix() {
        return prix.get();
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setPrix(Float prix) {
        this.prix = new SimpleFloatProperty(prix);
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getClub() {
        return club;
    }

    public void setClub(User club) {
        this.club = club;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQte() {
        return qte.get();
    }

    public void setQte(int qte) {
        this.qte = new SimpleIntegerProperty(qte);
    }
public SimpleIntegerProperty getQuantitProperty(){
        return qte;
    }

public SimpleFloatProperty getPrixProperty(){
        return prix;
    }
    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", ticket=" + ticket + ", user=" + user + ", club=" + club + ", qte=" + qte + '}';
    }

   
    
}

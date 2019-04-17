/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;



/**
 *
 * @author chaym
 */
public class Commande {
    
    private int id ; 
    private float total ; 
    private Date date ; 
    private User user ; 
    private User club ; 

    public Commande() {
    }

    public Commande(int id) {
        this.id = id;
    }

    public Commande(int id, float total, Date date) {
        this.id = id;
        this.total = total;
        this.date = date;
    }

    public Commande(float total, User user) {

        this.total = total;
      
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getClub() {
        return club;
    }

    public void setClub(User club) {
        this.club = club;
    }
    
    
    
}

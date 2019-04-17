/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import Services.PromotionServices;

/**
 *
 * @author Malek Ben Jemaa
 */
public class Recuperation {
    int id ; 
    int idTicket ; 
    float prix ; 
     private static Recuperation instance;
    public static Recuperation getInstance(){
        if(instance==null) 
            instance=new Recuperation();
        return instance;
    }

    public Recuperation() {
    }
    
    public Recuperation(int idTicket, float prix) {
        this.idTicket = idTicket;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Recuperation{" + "id=" + id + ", idTicket=" + idTicket + ", prix=" + prix + '}';
    }

  
    
    
    
}

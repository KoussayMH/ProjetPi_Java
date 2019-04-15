/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author kouss
 */
public class Event {
    private int id ; 
    private int ressource_id ; 
    private String titre ; 
    private String image1 ; 
    private Date date_event;
    private String description ;
    private String etat;
    private int note ;
    private String lieu ;
    private int nb_participants ;

    
     public Event() {
        
    }
    public Event(String titre, String image1, Date date_event, String description, String etat, int note, String lieu, int nb_participants) {
        this.titre = titre;
        this.image1 = image1;
        this.date_event = date_event;
        this.description = description;
        this.etat = etat;
        this.note = note;
        this.lieu = lieu;
        this.nb_participants = nb_participants;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public Date getDate_event() {
        return date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    
}

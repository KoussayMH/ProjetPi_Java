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
public class Comment {
        private int id;
        private Date date ;
        private int max_caracs;
        private String description ;
        private int Event_id;

    public Comment(int id, Date date, int max_caracs, String description) {
        this.id = id;
        this.date = date;
        this.max_caracs = max_caracs;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMax_caracs() {
        return max_caracs;
    }

    public void setMax_caracs(int max_caracs) {
        this.max_caracs = max_caracs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEvent_id() {
        return Event_id;
    }

    public void setEvent_id(int Event_id) {
        this.Event_id = Event_id;
    }
    
}

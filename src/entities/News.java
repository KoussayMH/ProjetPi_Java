/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author iheb
 */
public class News {
    private int id;
    private Club idClub;
    private String title;
    private String description;
    private Date creationDate;
    private String photo_news;

    public News(int id, Club idClub, String title, String description, Date creationDate, String photo_news) {
        this.id = id;
        this.idClub = idClub;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.photo_news = photo_news;
    }

    public News(Club idClub, String title, String description, Date creationDate, String photo_news) {
        this.idClub = idClub;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.photo_news = photo_news;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Club getIdClub() {
        return idClub;
    }

    public void setIdClub(Club idClub) {
        this.idClub = idClub;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getPhoto_news() {
        return photo_news;
    }

    public void setPhoto_news(String photo_news) {
        this.photo_news = photo_news;
    }
    
    
    
}

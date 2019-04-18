/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author iheb
 */
public class Club {
    private int id;
    private User admin_id;
    private String nameClub;
    private Date dateCreationClub;
    private String typeClub;
    private String statusClub;
    private String descriptionClub;
    private String photoClub;

    public Club(int id, User admin_id, String nameClub, Date dateCreationClub, String typeClub, String statusClub, String descriptionClub, String photoClub) {
        this.id = id;
        this.admin_id = admin_id;
        this.nameClub = nameClub;
        this.dateCreationClub = dateCreationClub;
        this.typeClub = typeClub;
        this.statusClub = statusClub;
        this.descriptionClub = descriptionClub;
        this.photoClub = photoClub;
    }
     public Club( User admin_id, String nameClub, Date dateCreationClub, String typeClub, String statusClub, String descriptionClub, String photoClub) {
        
        this.admin_id = admin_id;
        this.nameClub = nameClub;
        this.dateCreationClub = dateCreationClub;
        this.typeClub = typeClub;
        this.statusClub = statusClub;
        this.descriptionClub = descriptionClub;
        this.photoClub = photoClub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(User admin_id) {
        this.admin_id = admin_id;
    }

    public String getNameClub() {
        return nameClub;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }

    public Date getDateCreationClub() {
        return dateCreationClub;
    }

    public void setDateCreationClub(Date dateCreationClub) {
        this.dateCreationClub = dateCreationClub;
    }

    public String getTypeClub() {
        return typeClub;
    }

    public void setTypeClub(String typeClub) {
        this.typeClub = typeClub;
    }

    public String getStatusClub() {
        return statusClub;
    }

    public void setStatusClub(String statusClub) {
        this.statusClub = statusClub;
    }

    public String getDescriptionClub() {
        return descriptionClub;
    }

    public void setDescriptionClub(String descriptionClub) {
        this.descriptionClub = descriptionClub;
    }

    public String getPhotoClub() {
        return photoClub;
    }

    public void setPhotoClub(String photoClub) {
        this.photoClub = photoClub;
    }
    
    public String getNomProp() {
        return admin_id.getFirstName()+"  "+admin_id.getLastName();
    }

    @Override
    public String toString() {
        return  nameClub;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Club other = (Club) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}

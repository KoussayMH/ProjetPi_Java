/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Lenovo
 */
public class RatingProduit {
    private int id , id_client , id_produit, note;

    public RatingProduit() {
    }

    public RatingProduit(int id, int id_client, int id_produit, int note) {
        this.id = id;
        this.id_client = id_client;
        this.id_produit = id_produit;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "RatingProduit{" + "id=" + id + ", id_client=" + id_client + ", id_produit=" + id_produit + ", note=" + note + '}';
    }
    
    
    
    
    
}

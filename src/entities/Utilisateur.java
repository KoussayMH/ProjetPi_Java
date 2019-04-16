/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;
import javafx.scene.control.Button;

/**
 *
 * @author Lenovo
 */
public class Utilisateur {
    
    private int id,active,role;
    private String username,email,password,confirmation_token,reset_token;
    private Date date_reg;
    private String role_string,active_string;

     public Utilisateur(int active, int role, String username, String email, String password, String confirmation_token, String reset_token, Date date_reg) {
        this.active = active;
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmation_token = confirmation_token;
        this.reset_token = reset_token;
        this.date_reg = date_reg;
    }
       public Utilisateur(int id ,int active, int role, String username, String email, String confirmation_token) {
        this.active = active;
        this.id = id;
        this.role = role;
        this.username = username;
        this.email = email;
        this.confirmation_token = confirmation_token;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public Date getDate_reg() {
        return date_reg;
    }

    public void setDate_reg(Date date_reg) {
        this.date_reg = date_reg;
    }

    public String getRole_string() {
        return role_string;
    }

    public void setRole_string(String role_string) {
        this.role_string = role_string;
    }

    public String getActive_string() {
        return active_string;
    }

    public void setActive_string(String active_string) {
        this.active_string = active_string;
    }
   
    
    
    
    
}

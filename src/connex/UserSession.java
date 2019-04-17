/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Oussama Fezzani
 */
public final class UserSession {

    private static UserSession instance;

    private String username, email, confirmation_token;
    private int id, role, active;

    public UserSession(String username, String email, String confirmation_token, int id, int role, int active) {
        this.username = username;
        this.email = email;
        this.confirmation_token = confirmation_token;
        this.id = id;
        this.role = role;
        this.active = active;
    }

    public UserSession(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserSession() {
    }
    
    

    public static UserSession getInstance(String username, String email, String confirmation_token, int id, int role, int active) {
        if (instance == null) {
            instance = new UserSession(username, email, confirmation_token, id, role, active);
        }
        return instance;
    }

    public static UserSession getInstance() {
        if(instance == null)
        {
            instance = new UserSession();
        }
        return instance;
    }

    public static UserSession getInstance(String username, String email) {
        if (instance == null) {
            instance = new UserSession(username, email);
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public int getId() {
        return id;
    }

    public int getRole() {
        return role;
    }

    public int getActive() {
        return active;
    }

    public void cleanUserSession() {

        instance = null;

    }

    @Override
    public String toString() {
        return "UserSession{" + "username=" + username + ", email=" + email + ", confirmation_token=" + confirmation_token + ", id=" + id + ", role=" + role + ", active=" + active + '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

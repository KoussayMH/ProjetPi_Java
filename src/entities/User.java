/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author iheb
 */
public class User {
    private int id;
    private String username;
    private String email;
    private boolean enabled;
    private String password;
    private String firstName;
    private String lastName;
    private String descriptionUser;

    public User(int id, String username, String email, boolean enabled, String password, String firstName, String lastName, String descriptionUser) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.descriptionUser = descriptionUser;
    }

    public User(String username, String email, boolean enabled, String password, String firstName, String lastName, String descriptionUser) {
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.descriptionUser = descriptionUser;
    }

    public User(int id, String username, String email, boolean enabled, String firstName, String lastName, String descriptionUser) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.descriptionUser = descriptionUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescriptionUser() {
        return descriptionUser;
    }

    public void setDescriptionUser(String descriptionUser) {
        this.descriptionUser = descriptionUser;
    }

    @Override
    public String toString() {
        return firstName + lastName ;
    }
    
    
    
    
}

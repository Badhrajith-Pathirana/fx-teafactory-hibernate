/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author badhr
 */
@Entity
public class User {
    @Id
    private String username;
    private String password;
    private String acc_type;

    public User() {
    }

    public User(String username, String password, String acc_type) {
        this.username = username;
        this.password = password;
        this.acc_type = acc_type;
    }

    /**
     * @return the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param user the user to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the acc_type
     */
    public String getAcc_type() {
        return acc_type;
    }

    /**
     * @param acc_type the acc_type to set
     */
    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author snehaupadhyay
 */
@Entity
@Table(name = "sec_user")
@NamedQuery(name = "User.findAll",query = "select u from User u")
public class User implements Serializable {
    
    public User() {
    }
    
    @Id
    private String userName;
    
    private String password;
    
    private Boolean enabled;
    
    @ManyToMany
    @JoinTable(name = "sec_user_groups",
            joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))
    private List<Groups> groups = new ArrayList<>();
    
    
    
    public void addGroup(Groups g){
        if(!this.groups.contains(g)){
            this.groups.add(g);
        }
        if(!g.getUsers().contains(this)){
            g.getUsers().add(this);
        }
    }

    /**
     * Get the value of groups
     *
     * @return the value of groups
     */
    public List<Groups> getGroups() {
        return groups;
    }

    /**
     * Set the value of groups
     *
     * @param groups new value of groups
     */
    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }


    public User(String userName, String password, Boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    /**
     * Get the value of enabled
     *
     * @return the value of enabled
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Set the value of enabled
     *
     * @param enabled new value of enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @PrePersist
    @PreUpdate
    private void hashPassword(){
        String digestPassword = DigestUtils.md5Hex(this.password);
        this.password = digestPassword;
    }
    
}

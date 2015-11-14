/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author snehaupadhyay
 */
@Named
@RequestScoped
public class LoginBean extends AbstractJSFBean {
    
        private static final Logger LOG = Logger.getLogger(LoginBean.class.getName());
        
        @NotNull(message = "You shall not pass without a username!")
        private String username;
        
        @NotNull(message = "You shall not pass without a password!")
        @Size(min = 5, message = "Password must be at least 5 characters in length.")
        private String password;
        
    public LoginBean() {
    }
    
    
    @PostConstruct
    private void postConstruct(){
        super.postContruct();
    }
    
    public boolean isSneha() {
        return facesContext.getExternalContext().isUserInRole("Sneha");
    }
    
    public boolean isScott() {
        return facesContext.getExternalContext().isUserInRole("Scott");
    }
    
    public boolean isAmber() {
        return facesContext.getExternalContext().isUserInRole("Amber");
    }   
    
    public boolean isAdmin() {
        return facesContext.getExternalContext().isUserInRole("admin");
    }
    public String getPortalPathByRole(String path) {
        
        if(isSneha()) {
            path=path.replace("/student", "");
            return "/studentPortal" + path;
        } else if(isScott()) {
            path=path.replace("/faculty", "");
            return "/facultyPortal" + path;
        } else if(isAmber()) {
            path=path.replace("/advisor", "");
            return "/advisorPortal" + path;
        } else if(isAdmin()){
            path=path.replace("/admin", "");
            return "/admin" + path;
        } else {
            return path;
        }
    }  
    
    public String doLogin(){
        
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            try {
                req.login(username, password);
            } catch (ServletException ex) {
                LOG.log(Level.SEVERE, "There has been a problem invoking HttpServletRequest.login", ex);
                facesContext.addMessage(null, new FacesMessage("Bad Login", "Detail: You made a bad login!"));
                return "/login.xhtml";
            }
        return getPortalPathByRole("/welcome.xhtml?faces-redirect=true");
    }
    
    public String getRemoteUser(){
        return facesContext.getExternalContext().getRemoteUser();
    }
 
    public String doLogout(){
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            try {
                req.logout();
            } catch (ServletException ex) {
                LOG.log(Level.SEVERE, null, ex);
                facesContext.addMessage(null, new FacesMessage("Bad Logout", "Detail: Couldn't logout.Try Again!"));
                return "/login.xhtml";
            }
        
        return "/login.xhtml";
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
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
}

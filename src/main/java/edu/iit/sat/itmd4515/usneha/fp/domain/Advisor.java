/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.domain;

import edu.iit.sat.itmd4515.usneha.fp.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author snehaupadhyay
 */

@Entity
@Table(name="Advisor")
@NamedQueries({
    @NamedQuery(name="Advisor.findByName",query="select ad from Advisor ad where ad.advisorFName=:advisorFName"),
    @NamedQuery(name="Advisor.findById",query="select ad from Advisor ad where ad.id=:id"), 
    @NamedQuery(name="Advisor.findAll",query="select ad from Advisor ad"),
    @NamedQuery(name = "Advisor.findByUsername",query = "select ad from Advisor ad where ad.user.userName =:username"),
    
})
public class Advisor extends BaseEntity implements Serializable{

    public Advisor() {
    }
        // fields in entities
        private String advisorFName;
        private String advisorLName;
        private String advisorEmail;
        
        @OneToOne
        private User user;
        
        @OneToOne(cascade = CascadeType.PERSIST)
        private Department department;
        
        @OneToMany(mappedBy = "advisor",cascade = CascadeType.PERSIST)
        private List<Students> stud = new ArrayList<>();

        public List<Students> getStud() {
            return stud;
        }

        public void setStud(List<Students> stud) {
            this.stud = stud;
        }

    /**
     * Get the value of department
     *
     * @return the value of department
     */
    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Advisor{" + "advisorFName=" + advisorFName + ", advisorLName=" + advisorLName + ", advisorEmail=" + advisorEmail + ", user=" + user + ", department=" + department + ", stud=" + stud + '}';
    }

    /**
     * Set the value of department
     *
     * @param department new value of department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }


    public Advisor(String advisorFName, String advisorLName, String advisorEmail) {
        this.advisorFName = advisorFName;
        this.advisorLName = advisorLName;
        this.advisorEmail = advisorEmail;
    }
        
        

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }


    /**
     * Get the value of advisorEmail
     *
     * @return the value of advisorEmail
     */
    public String getAdvisorEmail() {
        return advisorEmail;
    }

    /**
     * Set the value of advisorEmail
     *
     * @param advisorEmail new value of advisorEmail
     */
    public void setAdvisorEmail(String advisorEmail) {
        this.advisorEmail = advisorEmail;
    }
  
    /**
     * Get the value of advisorLName
     *
     * @return the value of advisorLName
     */
    public String getAdvisorLName() {
        return advisorLName;
    }

    /**
     * Set the value of advisorLName
     *
     * @param advisorLName new value of advisorLName
     */
    public void setAdvisorLName(String advisorLName) {
        this.advisorLName = advisorLName;
    }


    /**
     * Get the value of advisorFName
     *
     * @return the value of advisorFName
     */
    public String getAdvisorFName() {
        return advisorFName;
    }

    /**
     * Set the value of advisorFName
     *
     * @param advisorFName new value of advisorFName
     */
    public void setAdvisorFName(String advisorFName) {
        this.advisorFName = advisorFName;
    }
    
}

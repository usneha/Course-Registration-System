/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author snehaupadhyay
 */

@Entity
@NamedQueries({
    @NamedQuery(name="Registration.findById",query="select r from Registration r where r.id=:id"), 
    @NamedQuery(name="Registration.findAll",query="select r from Registration r"), 
    
})

// @NamedQuery(name="Registration.findByName",query="select r from Registration r where r.name=:name"),
public class Registration implements Serializable{

    public Registration() {
    }
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        
//            @OneToOne
//            private Students students;

    /**
     * Get the value of students
     *
     * @return the value of students
     */
//    public Students getStudents() {
//        return students;
//    }

    /**
     * Set the value of students
     *
     * @param students new value of students
     */
//    public void setStudents(Students students) {
//        this.students = students;
//    }


    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date registrationDate;
    
        @Temporal(TemporalType.TIMESTAMP)
        private Date registrationDate;

    /**
     * Get the value of registrationDate
     *
     * @return the value of registrationDate
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Set the value of registrationDate
     *
     */
    @PreUpdate
    @PrePersist
    public void setRegistrationDate() {
        registrationDate = GregorianCalendar.getInstance().getTime();
    }

    
    private String registrationStatus;

    public Registration(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
    
    
    /**
     * Get the value of registrationDate
     *
     * @return the value of registrationDate
     */
//    public Date getRegistrationDate() {
//        return registrationDate;
//    }

    /**
     * Set the value of registrationDate
     *
     * @param registrationDate new value of registrationDate
     */
//    @PreUpdate
//    @PrePersist
//    public void setRegistrationDate() {
//        registrationDate = GregorianCalendar.getInstance().getTime();
//    }


    /**
     * Get the value of registrationStatus
     *
     * @return the value of registrationStatus
     */
    public String getRegistrationStatus() {
        return registrationStatus;
    }

    /**
     * Set the value of registrationStatus
     *
     * @param registrationStatus new value of registrationStatus
     */
   
    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
    
  


    /**
     * Get the value of name
     *
     * @return the value of name
     */
//    public String getName() {
//        return name;
//    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
//    public void setName(String name) {
//        this.name = name;
//    }

   
}

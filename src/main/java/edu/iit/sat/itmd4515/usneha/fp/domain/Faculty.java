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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="Faculty")
@NamedQueries({
    @NamedQuery(name="Faculty.findByName",query="select f from Faculty f where f.facultyFName=:facultyFName"),
    @NamedQuery(name="Faculty.findById",query="select f from Faculty f where f.id=:id"),
    @NamedQuery(name="Faculty.findAll",query="select f from Faculty f"),
    @NamedQuery(name = "Faculty.findByUsername",query = "select f from Faculty f where f.user.userName =:username"),
    @NamedQuery(name = "Faculty.findByUsername",query = "select f from Faculty f where f.user.userName =:username"),
    
})

//@NamedQuery(name = "Faculty.findByUsername",query = "select f from Faculty f where f.user.userName =:username")
    
public class Faculty extends BaseEntity implements Serializable {
    
     public Faculty(){
        
       } 
    private String facultyFName;
    private String facultyLName;
    private String facultyEmail;
    
    @ManyToMany (mappedBy = "faculty")
    @JoinTable(name = "Faculty_Students",
            joinColumns=@JoinColumn(name="faculty_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Students> students = new ArrayList<>();
    
//    @OneToMany
//    private List<Students> fStudent = new ArrayList<>();
    
//    @OneToOne(cascade = CascadeType.PERSIST)
//    private Courses courses;
    
//    @ManyToMany(mappedBy = "faculty",cascade = CascadeType.PERSIST)
    @ManyToMany(mappedBy = "faculty")
    private List<Courses> courses = new ArrayList<>();
    
//    @OneToOne(cascade = CascadeType.PERSIST)
    @OneToOne
    private Department department;
        
    @OneToOne
    private User user;

    public Faculty(String facultyFName, String facultyLName, String facultyEmail) {
        this.facultyFName = facultyFName;
        this.facultyLName = facultyLName;
        this.facultyEmail = facultyEmail;
    }
    
    public void addCourses(Courses c){
        if(!this.courses.contains(c)){
            this.courses.add(c);
        }
        if(!c.getFaculty().contains(this)){
            c.getFaculty().add(this);
        }
    }

    public List<Students> getStudents() {
        return students;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }
    
    /**
     * Get the value of facultyEmail
     *
     * @return the value of facultyEmail
     */
    public String getFacultyEmail() {
        return facultyEmail;
    }

    /**
     * Set the value of facultyEmail
     *
     * @param facultyEmail new value of facultyEmail
     */
    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public Faculty(String facultyFName, String facultyLName) {
        this.facultyFName = facultyFName;
        this.facultyLName = facultyLName;
    }

    /**
     * Get the value of courses
     *
     * @return the value of courses
     */
//    public Courses getCourses() {
//        return courses;
//    }

    /**
     * Set the value of courses
     *
     * @param courses new value of courses
     */
//    public void setCourses(Courses courses) {
//        this.courses = courses;
//    }

  
    
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
     * Get the value of facultyStudent
     *
     * @return the value of facultyStudent
     */
//    public List<Students> getFacultyStudent() {
//        return fStudent;
//    }
    
    /**
     * Get the value of department
     *
     * @return the value of department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Set the value of department
     *
     * @param department new value of department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }


    /**
     * Get the value of course
     *
     * @return the value of course
     */
    public List<Courses> getCourse() {
        return courses;
    }

    /**
     * Set the value of course
     *
     * @param cours new value of course
     */
    public void setCours(List<Courses> cours) {
        this.courses = cours;
    }

    /**
     * Get the value of facultyLName
     *
     * @return the value of facultyLName
     */
    public String getFacultyLName() {
        return facultyLName;
    }

    /**
     * Set the value of facultyLName
     *
     * @param facultyLName new value of facultyLName
     */
    public void setFacultyLName(String facultyLName) {
        this.facultyLName = facultyLName;
    }


    /**
     * Get the value of facultyFName
     *
     * @return the value of facultyFName
     */
    public String getFacultyFName() {
        return facultyFName;
    }

    /**
     * Set the value of facultyFName
     *
     * @param facultyFName new value of facultyFName
     */
    public void setFacultyFName(String facultyFName) {
        this.facultyFName = facultyFName;
    }
}

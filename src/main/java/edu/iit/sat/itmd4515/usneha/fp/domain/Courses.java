/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author snehaupadhyay
 */
@Entity
@Table(name="Courses")
@NamedQueries({
    @NamedQuery(name="Courses.findByName",query="select c from Courses c where c.courseName=:courseName"),
    @NamedQuery(name="Courses.findById",query="select c from Courses c where c.id=:id"), 
    @NamedQuery(name="Courses.findAll",query="select c from Courses c"), 
    
})

public class Courses extends BaseEntity implements Serializable{
    
    public Courses(){
        
    }
        @ManyToMany
        private List<Students> students = new ArrayList<>();
        
        @ManyToOne
        private Department department;
    
        private String courseName;
            
//        @JoinTable(name = "Courses_Students",
//                joinColumns=@JoinColumn(name="course_id"),
//                inverseJoinColumns = @JoinColumn(name = "student_id"))
//        @ManyToMany()
//        private List<Students> student = new ArrayList<>();

    
//        @ManyToMany(cascade = CascadeType.PERSIST)
        @ManyToMany
            @JoinTable(name = "Courses_Faculty",
                joinColumns = @JoinColumn(name = "course_id"),
                inverseJoinColumns = @JoinColumn(name = "faculty_id"))
        private List<Faculty> faculty = new ArrayList<>();
            

     public void addStudents(Students s){
        if(!this.students.contains(s)){
            this.students.add(s);
        }
        if(!s.getCourses().contains(this)){
            s.getCourses().add(this);
        }
    }

    public void setFaculty(List<Faculty> faculty) {
        this.faculty = faculty;
    }
     
     
    /**
     * Get the value of students
     *
     * @return the value of students
     */
    public List<Students> getStudents() {
        return students;
    }

    /**
     * Set the value of students
     *
     * @param students new value of students
     */
    public void setStudents(List<Students> students) {
        this.students = students;
    }


    public Courses(String courseName) {
        this.courseName = courseName;
    }
        
        
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
     * Get the value of faculty
     *
     * @return the value of faculty
     */
    public List<Faculty> getFaculty() {
        return faculty;
    }


    /**
     * Get the value of courseName
     *
     * @return the value of courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set the value of courseName
     *
     * @param courseName new value of courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

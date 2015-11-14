/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author snehaupadhyay
 */
@Entity
@Table(name="Department")
@NamedQueries({
    @NamedQuery(name="Department.findByName",query="select d from Department d where d.departmentName=:departmentName"),
    @NamedQuery(name="Department.findById",query="select d from Department d where d.id=:id"), 
    @NamedQuery(name="Department.findAll",query="select d from Department d"), 
    
})
public class Department extends BaseEntity implements Serializable {
    
    private String departmentName;
    
    @OneToMany(mappedBy = "department")
    private List<Students> students = new ArrayList<>();
    
//        @OneToMany(mappedBy = "department")
//        private List<Courses> courses = new ArrayList<>();

//    /**
//     * Get the value of courses
//     *
//     * @return the value of courses
//     */
////    public List<Courses> getCourses() {
////        return courses;
////    }
//
//    /**
//     * Set the value of courses
//     *
//     * @param courses new value of courses
//     */
////    public void setCourses(List<Courses> courses) {
////        this.courses = courses;
////    }


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

    
    public Department(){
        
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }
    
   
    /**
     * Get the value of departmentName
     *
     * @return the value of departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Set the value of departmentName
     *
     * @param departmentName new value of departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

   
}

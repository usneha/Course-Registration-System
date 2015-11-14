/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.domain;

import edu.iit.sat.itmd4515.usneha.fp.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author snehaupadhyay
 */
@Entity
@Table(name="Students")
@NamedQueries({
    @NamedQuery(name="Students.findByName",query="select s from Students s where s.studentFName=:studentFName"),
    @NamedQuery(name="Students.findById",query="select s from Students s where s.id=:id"), 
    @NamedQuery(name="Students.findAll",query="select s from Students s"),
    @NamedQuery(name= "Students.findByUsername",query = "select s from Students s where s.user.userName =:username"),
    @NamedQuery(name="Students.findAdvisor",query = "select s from Students s where s.advisor.id =:StudentAdvisorID"),
    
})
//@NamedQuery(name="Students.findFaculty",query = "select s from Students s where s.faculty.id =:facultyId"),
public class Students extends BaseEntity implements Serializable {
    
     public Students(){
     }
    private String studentFName;
    private String studentLName;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date studentDOB;
    private String studentAddress;
    private String studentEmail;
    private String studentDegreeType;
    
    @OneToOne
    private User user;
    
        @OneToOne
        private Department department;
        
        @OneToOne(cascade = CascadeType.PERSIST)
        private Registration registration;
        
        @ManyToMany(mappedBy = "Students")
        @JoinTable(name = "Courses_Students",
                joinColumns = @JoinColumn(name = "course_id"),
                inverseJoinColumns = @JoinColumn(name = "student_id"))
        private List<Courses> courses = new ArrayList<>();
        
//        @OneToOne
//        private Courses cors;
        
//        @OneToOne
//        @JoinColumn(name = "facultyId")
//        private Faculty facult;
        
        @ManyToOne
        @JoinColumn(name = "StudentAdvisorID")
        private Advisor advisor;
    
        @ManyToMany()
        private List<Faculty> faculty = new ArrayList<>();
        
        public void addFaculty(Faculty f){
        if(!this.faculty.contains(f)){
            this.faculty.add(f);
        }
        if(!f.getStudents().contains(this)){
            f.getStudents().add(this);
        }
        }
        
        public void addCourses(Courses c){
        if(!this.courses.contains(c)){
            this.courses.add(c);
        }
        if(!c.getStudents().contains(this)){
            c.getStudents().add(this);
        }
    }

    /**
     * Get the value of facult
     *
     * @return the value of facult
     */
//    public Faculty getFacult() {
//        return facult;
//    }

    /**
     * Set the value of facult
     *
     * @param facult new value of facult
     */
//    public void setFacult(Faculty facult) {
//        this.facult = facult;
//    }

        
//        @ManyToMany(mappedBy = "student",cascade = CascadeType.PERSIST)
//        private List<Courses> course = new ArrayList<>();
        
        
    /**
     * Get the value of cors
     *
     * @return the value of cors
     */
//    public Courses getCors() {
//        return cors;
//    }

    /**
     * Set the value of cors
     *
     * @param cors new value of cors
     */
//    public void setCors(Courses cors) {
//        this.cors = cors;
//    }

    /**
     * Get the value of courses
     *
     * @return the value of courses
     */
    public List<Courses> getCourses() {
        return courses;
    }

    /**
     * Set the value of courses
     *
     * @param courses new value of courses
     */
    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    /**
     * Get the value of registration
     *
     * @return the value of registration
     */
    public Registration getRegistration() {
        return registration;
    }

    /**
     * Set the value of registration
     *
     * @param registration new value of registration
     */
    public void setRegistration(Registration registration) {
        this.registration = registration;
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
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    public Students(String studentFName, String studentLName, Date studentDOB, String studentAddress, String studentEmail, String studentDegreeType) {
        this.studentFName = studentFName;
        this.studentLName = studentLName;
        this.studentDOB = studentDOB;
        this.studentAddress = studentAddress;
        this.studentEmail = studentEmail;
        this.studentDegreeType = studentDegreeType;
        
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
     * Get the value of faculty
     *
     * @return the value of faculty
     */
    public List<Faculty> getFaculty() {
        return faculty;
    }

    /**
     * Set the value of faculty
     *
     * @param faculty new value of faculty
     */
    public void setFaculty(List<Faculty> faculty) {
        this.faculty = faculty;
    }

    /**
     * Get the value of course
     *
     * @return the value of course
     */
//    public List<Courses> getCourse() {
//        return course;
//    }
    
    /**
     * Get the value of advisor
     *
     * @return the value of advisor
     */
    public Advisor getAdvisor() {
        return advisor;
    }

    /**
     * Set the value of advisor
     *
     * @param advisor new value of advisor
     */
    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
    
    /**
     * Get the value of studentDegreeType
     *
     * @return the value of studentDegreeType
     */
    public String getStudentDegreeType() {
        return studentDegreeType;
    }

    /**
     * Set the value of studentDegreeType
     *
     * @param studentDegreeType new value of studentDegreeType
     */
    public void setStudentDegreeType(String studentDegreeType) {
        this.studentDegreeType = studentDegreeType;
    }

    /**
     * Get the value of studentEmail
     *
     * @return the value of studentEmail
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * Set the value of studentEmail
     *
     * @param studentEmail new value of studentEmail
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    /**
     * Get the value of studentAddress
     *
     * @return the value of studentAddress
     */
    public String getStudentAddress() {
        return studentAddress;
    }

    /**
     * Set the value of studentAddress
     *
     * @param studentAddress new value of studentAddress
     */
    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    /**
     * Get the value of studentDOB
     *
     * @return the value of studentDOB
     */
    public Date getStudentDOB() {
        return studentDOB;
    }

    /**
     * Set the value of studentDOB
     *
     * @param studentDOB new value of studentDOB
     */
   
   @PreUpdate
   @PrePersist
   private void setStudentDOB(){
       studentDOB = GregorianCalendar.getInstance().getTime();
   }

    /**
     * Get the value of studentLName
     *
     * @return the value of studentLName
     */
    public String getStudentLName() {
        return studentLName;
    }

    /**
     * Set the value of studentLName
     *
     * @param studentLName new value of studentLName
     */
    public void setStudentLName(String studentLName) {
        this.studentLName = studentLName;
    }

    /**
     * Get the value of studentFName
     *
     * @return the value of studentFName
     */
    public String getStudentFName() {
        return studentFName;
    }

    /**
     * Set the value of studentFName
     *
     * @param studentFName new value of studentFName
     */
    public void setStudentFName(String studentFName) {
        this.studentFName = studentFName;
    }
}

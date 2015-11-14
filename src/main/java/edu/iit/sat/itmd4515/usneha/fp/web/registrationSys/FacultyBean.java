/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.web.registrationSys;

import edu.iit.sat.itmd4515.usneha.fp.domain.Advisor;
import edu.iit.sat.itmd4515.usneha.fp.domain.Courses;
import edu.iit.sat.itmd4515.usneha.fp.domain.Faculty;
import edu.iit.sat.itmd4515.usneha.fp.domain.Students;
import edu.iit.sat.itmd4515.usneha.fp.service.FacultyService;
import edu.iit.sat.itmd4515.usneha.fp.service.StudentsService;
import edu.iit.sat.itmd4515.usneha.fp.web.AbstractJSFBean;
import edu.iit.sat.itmd4515.usneha.fp.web.LoginBean;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author snehaupadhyay
 */
@Named
@RequestScoped
public class FacultyBean extends AbstractJSFBean{

    private static final Logger LOG = Logger.getLogger(FacultyBean.class.getName());
    
    @Inject LoginBean loginBean;
    @EJB private FacultyService facultyService;
    @EJB private StudentsService studentsService;
    private Students student;
    private List<Students> students;
    
    private List<Courses> courses;

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

    
    private Faculty facult;

    /**
     * Get the value of facult
     *
     * @return the value of facult
     */
    public Faculty getFacult() {
        return facult;
    }

    /**
     * Set the value of facult
     *
     * @param facult new value of facult
     */
    public void setFacult(Faculty facult) {
        this.facult = facult;
    }

    private List<Faculty> faculty;

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

    
    public FacultyBean() {
        super();
        LOG.info("Im in Faculty Bean");
    }
    
    private void refreshFaculty(){  
//        Integer faculty_Id = facultyService.findByUsername(loginBean.getRemoteUser()).getId();
//        students = studentsService.findFaculty(faculty_Id);
        facult = facultyService.findByUsername(loginBean.getRemoteUser());
//        Students s = new Students();
        students = facult.getStudents();
        courses = facult.getCourses();
        LOG.info("Student info is:" + student.toString());
    }
    
    @PostConstruct
    private void postConstruct(){
        super.postContruct();
        facult = new Faculty();
//        facult.
//        facult.
        student = new Students();
        
        student.setFaculty(faculty);
        
//        student.setFacult(new Faculty());
//        student.setCors(new Courses());
        student.setAdvisor(new Advisor());
        LOG.info("Inside Faculty Bean postConstruct");
        refreshFaculty();
    }
    
    
    
//    public String doCreate() {
//    // make sure that show refers to a new empty instance for the create form
//        student = new Students();
//        course = new Courses();
//        registration = new Registration();
//        refreshStudents();
//        return loginBean.getPortalPathByRole("/welcome.xhtml");
//    }
    
//    public String doDelete(Students student) {
//        LOG.info("The student to delete is " + student.toString());
//        studentService.delete(student);
//        refreshStudents();
//        facesContext.addMessage(null,new FacesMessage("Student Deleted", "Student"+
//                student.getStudentFName()+"has been deleted"));
//        return loginBean.getPortalPathByRole("/welcome.xhtml");
//    }
    
    // step 1 for update
    public String doUpdate(Students student) {
        this.student = student;
        LOG.info("The student to update is " + student.toString());
        // can not do faces redirect here... needs to be postback with their JS
        //return loginBean.getPortalPathByRole("/show.xhtml");
        refreshFaculty();
        return "/advisorPortal/student.xhtml";
    }

//    // step 2 for Update/Create
//    public String executeSave() {
//        if (this.student.getId() != null) {
//            LOG.info("Executing update on " + this.student.toString());
//            facultyService.update(facult);
//            refreshFaculty();
//            return loginBean.getPortalPathByRole("/welcome.xhtml");
//        } else {
//            LOG.info("Creating " + this.student.toString());
//            studentService.create(student);
//            refreshFaculty();
//            return loginBean.getPortalPathByRole("/welcome.xhtml");
//        }
//    }
    
    
    

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


    /**
     * Get the value of students
     *
     * @return the value of students
     */
    public Students getStudent() {
        return student;
    }

    /**
     * Set the value of students
     *
     * @param students new value of students
     */
    public void setStudents(Students students) {
        this.student = students;
    }

}

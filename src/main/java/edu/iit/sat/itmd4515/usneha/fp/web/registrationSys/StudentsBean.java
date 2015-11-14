/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.web.registrationSys;

import edu.iit.sat.itmd4515.usneha.fp.domain.Advisor;
import edu.iit.sat.itmd4515.usneha.fp.domain.Courses;
import edu.iit.sat.itmd4515.usneha.fp.domain.Department;
import edu.iit.sat.itmd4515.usneha.fp.domain.Registration;
import edu.iit.sat.itmd4515.usneha.fp.domain.Students;
import edu.iit.sat.itmd4515.usneha.fp.service.AdvisorService;
import edu.iit.sat.itmd4515.usneha.fp.service.CoursesService;
import edu.iit.sat.itmd4515.usneha.fp.service.FacultyService;
import edu.iit.sat.itmd4515.usneha.fp.service.RegistrationService;
import edu.iit.sat.itmd4515.usneha.fp.service.StudentsService;
import edu.iit.sat.itmd4515.usneha.fp.web.AbstractJSFBean;
import edu.iit.sat.itmd4515.usneha.fp.web.LoginBean;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
//import org.apache.commons.mail.*;

/**
 *
 * @author snehaupadhyay
 */
@Named
@RequestScoped
public class StudentsBean extends AbstractJSFBean {
    private static final Logger LOG = Logger.getLogger(StudentsBean.class.getName());
    
    @EJB private StudentsService studentService;
    @EJB private AdvisorService advisorService;
    @EJB private FacultyService facultyService;
    @EJB private CoursesService coursesService;
    @EJB private RegistrationService registrationService;
    private Students student;
    private Courses course;
    
    private List<Courses> courses;
    
    private Registration registration;
    private List<Students> students;
    @Inject LoginBean loginBean;
    @Inject EmailUser emailUser;
    
    @PostConstruct
    private void postConstruct(){
        super.postContruct();
        student = new Students();
        student.setAdvisor(new Advisor());
        student.setRegistration(new Registration());
        student.setDepartment(new Department());
        course = new Courses();
        refresh();
    }
    
    public void refresh(){
        student = studentService.findByUsername(loginBean.getRemoteUser());
        courses = student.getCourses();
        advisor = student.getAdvisor();
    }
    
//    public String register(){
//        student = studentService.findByUsername(loginBean.getRemoteUser());
//       
//       List<Courses> courses1 = student.getCourses();
//       courses = coursesService.findAll();
//            
//         for(int i=0; i <courses1.size();i++){
//            for(Courses c:courses){
//                if(c.getCourseName().equals(courses1.get(i).getCourseName())){
//                    c.setCourseName(courses1.get(i).getCourseName());
//                    student.addCourses(c);
//                    c.addStudents(student);
//                    break;
//                }
//              }
//            }
////        course.addStudents(student);
////        student.addCourses(course);
////        coursesService.update(course);
//        studentService.update(student);
//        return loginBean.getPortalPathByRole("/welcome.xhtml");
//    }
//    
    // step 1 for update
    public String doUpdate(Students student) {
        this.student = student;
        LOG.info("The student to update is " + student.toString());
        student.getUser().getUserName();
        
        // can not do faces redirect here... needs to be postback with their JS
        //return loginBean.getPortalPathByRole("/show.xhtml");
        refresh();
        return "/studentPortal/student.xhtml";
    }
//    public String doDelete(Students student) {
//        student = studentService.findByUsername(loginBean.getRemoteUser());
//        
//         List<Courses> courses1 = student.getCourses();
//       courses = coursesService.findAll();
//            
//         for(int i=0;i<courses1.size();i++){
//            for(Courses c:courses){
//                if(c.getCourseName().equals(courses1.get(i).getCourseName())){
//                    c.setCourseName(courses1.get(i).getCourseName());
////                    student.addCourses(c);
////                    student.getCourses().remove(c);
//                    c.getStudents().remove(student);
//                    break;
//                }
//              }
//            }
//        
//        LOG.info("The Course to delete is " + student.toString());
//        studentService.update(student);
//        
//        facesContext.addMessage(null,new FacesMessage("Course dropped", "Course has been deleted"));
//        refresh();
//        return loginBean.getPortalPathByRole("/welcome.xhtml");
//    }
//    
    // step 2 for Update/Create
    public String executeSave() throws UnsupportedEncodingException {
        if (this.student.getId() != null) {
            LOG.info("Executing update on " + this.student.toString());
            studentService.update(student);
            refresh();
            return loginBean.getPortalPathByRole("/welcome.xhtml");
        } else {
            LOG.info("Creating " + this.student.toString());
            LOG.info("Creating " + this.student.getStudentEmail());
            studentService.create(student);
            String email = student.getStudentEmail();
            emailUser.sendEmail(email);
            refresh();
            return loginBean.getPortalPathByRole("/welcome.xhtml");
        }
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public Courses getCourse() {
        return course;
    }
    
    private Advisor advisor;

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
     * Get the value of emailUser
     *
     * @return the value of emailUser
     */
    public EmailUser getEmailUser() {
        return emailUser;
    }

    /**
     * Set the value of emailUser
     *
     * @param emailUser new value of emailUser
     */
    public void setEmailUser(EmailUser emailUser) {
        this.emailUser = emailUser;
    }

    
     public StudentsBean() {
         super();
         LOG.info("Im in stud bean");
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

    /**
     * Get the value of student
     *
     * @return the value of student
     */
    public Students getStudent() {
        return student;
    }

    /**
     * Set the value of student
     *
     * @param student new value of student
     */
    public void setStudent(Students student) {
        this.student = student;
    }
}

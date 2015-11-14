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
import edu.iit.sat.itmd4515.usneha.fp.security.Groups;
import edu.iit.sat.itmd4515.usneha.fp.security.User;
import edu.iit.sat.itmd4515.usneha.fp.service.AdvisorService;
import edu.iit.sat.itmd4515.usneha.fp.service.CoursesService;
import edu.iit.sat.itmd4515.usneha.fp.service.FacultyService;
import edu.iit.sat.itmd4515.usneha.fp.service.GroupService;
import edu.iit.sat.itmd4515.usneha.fp.service.RegistrationService;
import edu.iit.sat.itmd4515.usneha.fp.service.StudentsService;
import edu.iit.sat.itmd4515.usneha.fp.service.UserService;
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

/**
 *
 * @author snehaupadhyay
 */
@Named
@RequestScoped
public class AdvisorBean extends AbstractJSFBean {
    private static final Logger LOG = Logger.getLogger(AdvisorBean.class.getName());
    private Advisor advisor;
    
    @EJB private StudentsService studentService;
    @EJB private AdvisorService advisorService;
    @EJB private FacultyService facultyService;
    @EJB private CoursesService coursesService;
    @EJB private RegistrationService registrationService;
    @EJB private GroupService groupService;
    @EJB private UserService userService;
    private Students student;
    private Courses course;
    private Registration registration;
    private List<Students> students;
    @Inject LoginBean loginBean;
    @Inject EmailUser emailUser;
    
    
     public AdvisorBean() {
        super();
    }
    
         private List<Groups> groups;

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

    /**
     *
     */
    @PostConstruct
    private void postConstruct(){
        super.postContruct();
        student = new Students();
        student.setAdvisor(new Advisor());
        student.setRegistration(new Registration());
        student.setDepartment(new Department());
        student.setUser(new User());
        LOG.info("Inside advisor bean post construct");
        refreshStudents();
    }
    private void refreshStudents(){
        Integer advisor_Id = advisorService.findByUsername(loginBean.getRemoteUser()).getId();
        students = studentService.findAdvisor(advisor_Id);
         LOG.info("Student info is:" + students.toString());
    }
    
    public void refreshAdvisor(){
        advisor = advisorService.findByUsername(loginBean.getRemoteUser());
        students = advisor.getStud();
    }
    
     public String doCreate() {
    // make sure that show refers to a new empty instance for the create form
        student = new Students();
        course = new Courses();
        registration = new Registration();
        refreshStudents();
        return loginBean.getPortalPathByRole("/welcome.xhtml");
    }
    
    public String doDelete(Students student) {
        LOG.info("The student to delete is " + student.toString());
        studentService.delete(student);
        refreshStudents();
        facesContext.addMessage(null,new FacesMessage("Student Deleted", "Student"+
                student.getStudentFName()+"has been deleted"));
        return loginBean.getPortalPathByRole("/welcome.xhtml");
    }
    
    // step 1 for update
    public String doUpdate(Students student) {
        this.student = student;
        student.getUser().setPassword("abc123");
        student.getUser().getUserName();
        LOG.info("The student to update is " + student.toString());
        // can not do faces redirect here... needs to be postback with their JS
        //return loginBean.getPortalPathByRole("/show.xhtml");
//        studentService.update(student);
        refreshStudents();
        return "/advisorPortal/student.xhtml";
    }

    // step 2 for Update/Create
    public String executeSave() throws UnsupportedEncodingException {
        if (this.student.getId() != null) {
            LOG.info("Executing update on " + this.student.toString());
            studentService.update(student);
            refreshStudents();
            return loginBean.getPortalPathByRole("/welcome.xhtml");
        } else {
            LOG.info("Creating " + this.student.toString());
            LOG.info("Creating " + this.student.getStudentEmail());
            
            User u = student.getUser();
            u.setUserName(student.getUser().getUserName());
            u.setPassword("abc123");
            
            groups = groupService.findAll();
            
            for(Groups g:groups){
                if(g.getGroupName().equals("Students")){
                   u.addGroup(g);
                }
            }
            
            userService.create(u);
            studentService.create(student);
            String email = student.getStudentEmail();
            emailUser.sendEmail(email);
            refreshStudents();
            return loginBean.getPortalPathByRole("/welcome.xhtml");
        }
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

}

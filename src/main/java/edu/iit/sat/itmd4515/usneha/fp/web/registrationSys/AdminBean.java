/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.web.registrationSys;

import edu.iit.sat.itmd4515.usneha.fp.domain.Advisor;
import edu.iit.sat.itmd4515.usneha.fp.domain.Courses;
import edu.iit.sat.itmd4515.usneha.fp.domain.Department;
import edu.iit.sat.itmd4515.usneha.fp.domain.Faculty;
import edu.iit.sat.itmd4515.usneha.fp.domain.Registration;
import edu.iit.sat.itmd4515.usneha.fp.domain.Students;
import edu.iit.sat.itmd4515.usneha.fp.security.Groups;
import edu.iit.sat.itmd4515.usneha.fp.security.User;
import edu.iit.sat.itmd4515.usneha.fp.service.AdvisorService;
import edu.iit.sat.itmd4515.usneha.fp.service.CoursesService;
import edu.iit.sat.itmd4515.usneha.fp.service.DepartmentService;
import edu.iit.sat.itmd4515.usneha.fp.service.FacultyService;
import edu.iit.sat.itmd4515.usneha.fp.service.GroupService;
import edu.iit.sat.itmd4515.usneha.fp.service.RegistrationService;
import edu.iit.sat.itmd4515.usneha.fp.service.StudentsService;
import edu.iit.sat.itmd4515.usneha.fp.service.UserService;
import edu.iit.sat.itmd4515.usneha.fp.web.AbstractJSFBean;
import edu.iit.sat.itmd4515.usneha.fp.web.LoginBean;
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
public class AdminBean extends AbstractJSFBean {
    private static final Logger LOG = Logger.getLogger(AdminBean.class.getName());
    
    @EJB private StudentsService studentsService;
    @EJB private CoursesService coursesService;
    @EJB private AdvisorService advisorService;
    @EJB private FacultyService facultyService;
    @EJB private DepartmentService departmentService;
//    @EJB private StudentsService studentsService;
    @EJB private RegistrationService registrationService;
    @EJB private UserService userService;
    @EJB private GroupService groupService;
    @Inject LoginBean loginBean;
    @Inject EmailUser emailUser;
    
    private List<Groups> groups;
    private Students student;
    private List<Students> students;
    private Courses course;
    private List<Courses> courses;
    private Advisor advisor;
    private List<Advisor> advisors;
    private Faculty facult;
    private List<Faculty> faculty;
    private Department department;    
    private List<Department> departments;
    private Registration registration;
    private List<Registration> registrations;

    /**
     * Get the value of registrations
     *
     * @return the value of registrations
     */
    public List<Registration> getRegistrations() {
        return registrations;
    }

    /**
     * Set the value of registrations
     *
     * @param registrations new value of registrations
     */
    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }


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
     * Get the value of departments
     *
     * @return the value of departments
     */
    public List<Department> getDepartments() {
        return departments;
    }

    /**
     * Set the value of departments
     *
     * @param departments new value of departments
     */
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
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

   
    
    public AdminBean() {
        super();
        LOG.info("Im in Admin Bean");
    }
    
    @PostConstruct
    private void postConstruct(){
        super.postContruct();
        student = new Students();
        student.setAdvisor(new Advisor());
        student.setDepartment(new Department());
        student.setRegistration(new Registration());
        student.setUser(new User());
        
        course = new Courses();
        course.setDepartment(new Department());
        
        
        advisor = new Advisor();
        advisor.setDepartment(new Department());
        advisor.setUser(new User());
        
        facult = new Faculty();
        facult.setUser(new User());
        
        facult.setDepartment(new Department());
        LOG.info("Inside Admin bean post construct");
        refreshAdmin();
        
    }
    
    private void refreshAdmin(){
        students = studentsService.findAll();
        advisors = advisorService.findAll();
        courses = coursesService.findAll();
    }
    
    public void refresh(){
        student = studentsService.findByUsername(loginBean.getRemoteUser());
        courses = student.getCourses();
        advisor = student.getAdvisor();
    }
   
    // step 2 for Update/Create
    public String executeStudentSave() {
        if (this.student.getId() != null) {
            LOG.info("Executing update on " + this.student.toString());
            studentsService.update(student);
            refreshAdmin();
            return loginBean.getPortalPathByRole("/welcome.xhtml");
        } else {    
        
        LOG.info("Creating " + this.student.toString());
            
            User u = student.getUser();
            u.setUserName(student.getUser().getUserName());
            u.setPassword(student.getUser().getPassword());
            
            groups = groupService.findAll();
            
            for(Groups g:groups){
                if(g.getGroupName().equals("Students")){
                   u.addGroup(g);
                }
            }
  
            userService.create(u);
            studentsService.create(student);
            refreshAdmin();
            return loginBean.getPortalPathByRole("/welcome.xhtml");
        }
            
        
    }
    public String executeAdvisorSave() {
            LOG.info("Creating " + this.advisor.toString());
            
            User u = advisor.getUser();
            u.setUserName(advisor.getUser().getUserName());
            u.setPassword(advisor.getUser().getPassword());
            
            groups = groupService.findAll();
            
            for(Groups g:groups){
                if(g.getGroupName().equals("Advisor")){
                   u.addGroup(g);
                }
            }
           
            userService.create(u);
            advisorService.create(advisor);
            emailUser.sendEmail(advisor.getAdvisorEmail());
            refreshAdmin();
            return loginBean.getPortalPathByRole("/welcome.xhtml");
        
    }
    
    public String executeCourseSave(){
        
        LOG.info("Creating " + this.course.toString());

            coursesService.create(course);
            refreshAdmin();
            return loginBean.getPortalPathByRole("/welcome.xhtml");
            
    }
    public String executeFacultySave() {
            LOG.info("Creating " + this.facult.toString());
            User u = facult.getUser();
            u.setUserName(facult.getUser().getUserName());
            u.setPassword(facult.getUser().getPassword());
            
            groups = groupService.findAll();
            
            for(Groups g:groups){
                if(g.getGroupName().equals("Faculty")){
                   u.addGroup(g);
                }
            }
            
            String dName = facult.getDepartment().getDepartmentName();
            departments = departmentService.findAll();
            Department dep = new Department();
            for(Department d:departments){
                if(facult.getDepartment().getDepartmentName().equals(dName)){
                    facult.setDepartment(d);
                    dep=d;
                    break;
                }
              }
            
            userService.create(u);
            facultyService.create(facult);
//            emailUser.sendEmail(advisor.getAdvisorEmail());
            refreshAdmin();
            return loginBean.getPortalPathByRole("/welcome.xhtml");
        
    }
//    public String executeFacultySave() {
//            LOG.info("Creating " + this.facult.toString());
////            facultyService.create(facult, facult.getCourses());
//            //Faculty f = new Faculty();
//            
//            String dName = facult.getDepartment().getDepartmentName();
//            departments = departmentService.findAll();
//          
//            Department dep = new Department();
//            for(Department d:departments){
//                if(facult.getDepartment().getDepartmentName().equals(dName)){
//                    facult.setDepartment(d);
//                    dep=d;
//                    break;
//                }
//              }
//            
////            for(int i=0;i<=courses.size();i++){
////                if()
////                courses.get(i).getCourseName();
////            }
////            String cName = facult.getCourse().getCourseName();
////            courses = coursesService.findAll();
////            int i = 0;
////            for(Courses c:courses){
////                if(facult.getCourses().getCourseName().equals(cName)){
////                    c.setDepartment(dep);
////                    facult.setCourses(c);
////                    break;
////                }
////            }
////            
//           
//            
////            facult.setDepartment(departmentService.findByName(facult.getDepartment().getDepartmentName()));
//            
////            facultyService.create(facult);
//////            emailUser.sendEmail(facult.getFacultyEmail());
////            refreshAdmin();
////            return loginBean.getPortalPathByRole("/welcome.xhtml");
//        
//    }
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

    /**
     * Get the value of advisors
     *
     * @return the value of advisors
     */
    public List<Advisor> getAdvisors() {
        return advisors;
    }

    /**
     * Set the value of advisors
     *
     * @param advisors new value of advisors
     */
    public void setAdvisors(List<Advisor> advisors) {
        this.advisors = advisors;
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
     * Get the value of course
     *
     * @return the value of course
     */
    public Courses getCourse() {
        return course;
    }

    /**
     * Set the value of course
     *
     * @param course new value of course
     */
    public void setCourse(Courses course) {
        this.course = course;
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
    
    public String register(){
        student = studentsService.findByUsername(loginBean.getRemoteUser());
          String cName = course.getCourseName();
            courses = coursesService.findAll();
            Courses cour = new Courses();
            for(Courses c:courses){
                if(c.getCourseName().equals(cName)){
                   cour = c;
                    break;
                }
              }
            
           List<Courses> courses1 = student.getCourses();
            for(Courses c:courses1){
                if(!c.getCourseName().equals(cName)){
//                   cour = c;
                   cour.addStudents(student);
                    break;
                }
              }

        cour.setDepartment(student.getDepartment());
        student.addCourses(cour);
        student.getCourses().add(cour);
        cour.getStudents().add(student);
        
        studentsService.update(student);
        coursesService.update(cour);
        return loginBean.getPortalPathByRole("/welcome.xhtml");
    }
    
    public String doStudentUpdate(Students student){
        LOG.info("Executing update on " + this.student.toString());
            studentsService.update(student);
            refreshAdmin();
            return "/admin/student.xhtml";
    }
    public String doDelete(Students student) {
        student = studentsService.findByUsername(loginBean.getRemoteUser());
//        student.getCourses().remove(c1);
//        c1.getStudents().remove(student);
      
        LOG.info("The Course to delete is " + student.toString());
          studentsService.update(student);
        facesContext.addMessage(null,new FacesMessage("Course dropped", "Course has been deleted"));
        refresh();
        return loginBean.getPortalPathByRole("/welcome.xhtml");
    }
    
    public void findAdvisor(){
        student = studentsService.findByUsername(loginBean.getRemoteUser());
        Integer advisorId = student.getAdvisor().getId();
        studentsService.findAdvisor(advisorId);
//        refresh();
//        return loginBean.getPortalPathByRole("/welcome.xhtml");
    }
}

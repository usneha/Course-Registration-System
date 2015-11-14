/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.service;

import edu.iit.sat.itmd4515.usneha.fp.domain.Advisor;
import edu.iit.sat.itmd4515.usneha.fp.domain.Courses;
import edu.iit.sat.itmd4515.usneha.fp.domain.Department;
import edu.iit.sat.itmd4515.usneha.fp.domain.Faculty;
import edu.iit.sat.itmd4515.usneha.fp.domain.Registration;
import edu.iit.sat.itmd4515.usneha.fp.domain.Students;
import edu.iit.sat.itmd4515.usneha.fp.security.Groups;
import edu.iit.sat.itmd4515.usneha.fp.security.User;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author snehaupadhyay
 */

@Startup
@Singleton
public class DatabasePopulator {
    
    @EJB
    private UserService userService;
    
    @EJB
    private GroupService groupService;
    
    @EJB
    private StudentsService studentsService;
    
    @EJB
    private RegistrationService registrationService;
    
    @EJB
    private FacultyService facultyService;
    
    @EJB
    private DepartmentService departmentService;
    
    @EJB
    private CoursesService coursesService;
    
    @EJB
    private AdvisorService advisorService;
    
      
    public DatabasePopulator() {
    }
    

    @PostConstruct
    private void seedDatabase(){
        
        User admin = new User("admin","admin");
        Groups admin_group = new Groups("Admin","Administrator Group");
        admin.addGroup(admin_group);
        
        Groups students_group = new Groups("Students","Student Group");
        Groups faculty_group = new Groups("Faculty","Faculty Group");
        Groups advisor_group = new Groups("Advisor","Advisor Group");
        
        User Scott = new User("Scott","abc123");
        User Sneha = new User("Sneha","abc123");
        User Papademas = new User("Papademas","abc123");
        User Amber = new User("Amber","abc123");
        User Thanu = new User("Thanu","abc123");
        
        Scott.addGroup(faculty_group);
        Sneha.addGroup(students_group);
        Papademas.addGroup(faculty_group);
        Amber.addGroup(advisor_group);
        Thanu.addGroup(students_group);
        
        Department itm = new Department("ITM");
        Department cs = new Department("CS");
        
        Registration reg1 = new Registration("Fall");
        Registration reg2 = new Registration("Spring");
        Registration reg3 = new Registration("Summer");
        
        Courses c1 = new Courses("Java EE");
        Courses c2 = new Courses("DB");
        c1.setDepartment(itm);
        c2.setDepartment(itm);
        
        //to be done for registration as well        
        Students s1 = new Students("Sneha","Upadhyay",new GregorianCalendar(1991, 0, 12).getTime(),
                "SKD","email", "Masters");
        s1.setUser(Sneha);
        s1.setDepartment(itm);
        s1.setRegistration(reg1);
        
        Students s2 = new Students("Thanu","Chandrahasa",new GregorianCalendar(1991, 0, 12).getTime(),
                "Chicago","tchnadr@hawk.iit.edu", "Masters");
        s2.setUser(Thanu);
        s2.setDepartment(itm);
        s2.setRegistration(reg1);
//        s2.setCors(c1);
        
        Advisor a = new Advisor("Amber","Chatellier","achatell@iit.edu");
        a.setUser(Amber);
        s1.setAdvisor(a);
        s2.setAdvisor(a);
        a.setDepartment(itm);
        
//        List<Courses> c1 = new ArrayList<Courses>("Java EE","Big Data");
        
        Faculty f1 = new Faculty("Scott","Spyrison","sspyriso@hawk.iit.edu");
        f1.setUser(Scott);
        f1.setDepartment(itm);
//        f1.setCourses(c1);
//        c1.setFacult(f1);
        
        
//        s1.setFaculty(f1);
        
        s1.addFaculty(f1);
        s2.addFaculty(f1);
//        s1.setFacult(f1);
//        s2.setFacult(f1);
        
        c1.addStudents(s1);
        c1.addStudents(s2);
        f1.addCourses(c1);
        
        groupService.create(faculty_group);
        groupService.create(students_group);
        groupService.create(advisor_group);
        groupService.create(admin_group);
        
        userService.create(Scott);
        userService.create(Sneha);
        userService.create(Papademas);
        userService.create(Amber);
        userService.create(admin);
        userService.create(Thanu);
        
        departmentService.create(itm);
        departmentService.create(cs);
        
        facultyService.create(f1);
        
        coursesService.create(c1);
        coursesService.create(c2);
        
        advisorService.create(a);
       
        
        registrationService.create(reg1);
        reg1.setRegistrationStatus("fall");
//        System.out.println(registrationService.find(reg1));
        registrationService.update(reg1);
        registrationService.create(reg2);
        registrationService.create(reg3);
        registrationService.delete(reg3);
        
        studentsService.create(s1);
        studentsService.create(s2);
        
    }
    
}

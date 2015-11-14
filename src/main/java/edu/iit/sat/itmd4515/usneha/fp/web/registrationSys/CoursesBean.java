/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.web.registrationSys;

import edu.iit.sat.itmd4515.usneha.fp.domain.Courses;
import edu.iit.sat.itmd4515.usneha.fp.domain.Department;
import edu.iit.sat.itmd4515.usneha.fp.domain.Faculty;
import edu.iit.sat.itmd4515.usneha.fp.service.CoursesService;
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
public class CoursesBean extends AbstractJSFBean {
    private static final Logger LOG = Logger.getLogger(CoursesBean.class.getName());

    @EJB private CoursesService coursesService;
    
    
    private Courses course;
    @Inject LoginBean loginBean;
    
    private List<Courses> courses;
    
    public CoursesBean() {
        super();
        LOG.info("Im in Course Bean");
    }

    @PostConstruct
    private void postConstruct(){
        super.postContruct();
        course = new Courses();
        course.setDepartment(new Department());
//        course.setFacult(new Faculty());
        refreshCourses();
        LOG.info("Inside Course Bean postConstruct");
    }
    
    private void refreshCourses(){
        courses = coursesService.findAll();
//        Integer student_courseId = student.getCors().getId();
        LOG.info("Courses info is:" + courses.toString());
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

    
    
    
    
    
    
}

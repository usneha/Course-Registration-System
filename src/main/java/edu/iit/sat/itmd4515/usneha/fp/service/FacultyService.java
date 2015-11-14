/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.service;

import edu.iit.sat.itmd4515.usneha.fp.domain.Courses;
import edu.iit.sat.itmd4515.usneha.fp.domain.Department;
import edu.iit.sat.itmd4515.usneha.fp.domain.Faculty;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.persistence.jpa.JpaHelper;

/**
 *
 * @author snehaupadhyay
 */
@Named
@Stateless
public class FacultyService {
    
    @PersistenceContext(unitName = "usnehaPU")
    private EntityManager em;

    public FacultyService() {
    }

    public void create(Faculty f) {
        em.persist(f);
    }

    public void update(Faculty f) {
        em.merge(f);
    }

    public void remove(Faculty f) {
        em.remove(f);
    }

    public Faculty find(long id) {
        return em.find(Faculty.class, id);
    }

   
    
    public Faculty findByUsername(String userName){
        return em.createNamedQuery("Faculty.findByUsername",
                Faculty.class)
                .setParameter("username",userName)
                .getSingleResult();
    }

   
    public List<Faculty> findAll() {
        return em.createNamedQuery("Faculty.findAll",
                Faculty.class).getResultList();
    }
    public void create(Faculty faculty, Courses courses){
        // faculty is unhandled coming from JSF and needs to be managed
        faculty = em.getReference(Faculty.class, faculty.getId());
        
//        courses = em.getReference(Courses.class, courses.getId());
        
        //and set it in the new course
//        faculty.setCourses(courses);
        faculty.getCourse().add(courses);
        
        em.persist(faculty);
        
    }
    
    
//    public void delete(Faculty faculty, Courses courses, Department department){
//        
//        // these parameters coming into this method are NOT managed
//        
//        faculty = em.getReference(Faculty.class, faculty.getId());
//        courses = em.getReference(Courses.class, courses.getId());
//        department = em.getReference(Department.class,department.getId());
//        
//        faculty.getCourse().remove(courses);
//        courses.getFaculty().remove(faculty);
//        
//        if(faculty.getCourse().isEmpty()){
//            faculty.getCourse().remove(courses);
//            faculty.setCours(null);
//            em.remove(faculty);
//        }
//    }
}

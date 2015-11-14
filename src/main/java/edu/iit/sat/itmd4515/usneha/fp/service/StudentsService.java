/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.service;

import edu.iit.sat.itmd4515.usneha.fp.domain.Courses;
import edu.iit.sat.itmd4515.usneha.fp.domain.Students;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

/**
 *
 * @author snehaupadhyay
 */
@Named
@Stateless
public class StudentsService extends AbstractService<Students>{


    public StudentsService() {
        super(Students.class);
    }

    public void create(Students s) {
        getEntityManager().persist(s);
    }

    public void update(Students s) {
        getEntityManager().merge(s);
    }

    public void remove(Students s) {
        getEntityManager().remove(s);
    }

    public Students find(long id) {
        return getEntityManager().find(Students.class, id);
    }

   
    
    public Students findByUsername(String userName){
        return getEntityManager().createNamedQuery("Students.findByUsername",
                Students.class)
                .setParameter("username",userName)
                .getSingleResult();
    }
    
    public List<Students> findAdvisor(Integer id){
        return getEntityManager().createNamedQuery("Students.findAdvisor",
                Students.class)
                .setParameter("StudentAdvisorID",id).getResultList();
//                .getSingleResult();
    }
    
//    public List<Students> findFaculty(Integer id){
//        return getEntityManager().createNamedQuery("Students.findFaculty",
//                Students.class)
//                .setParameter("facultyId",id).getResultList();
////                .getSingleResult();
//    }
    
   @Override
    public List<Students> findAll() {
        return getEntityManager().createNamedQuery("Students.findAll",
                Students.class).getResultList();
    }
    
    public void delete(Students student,Courses course){
        
        course = getEntityManager().getReference(Courses.class, course.getId());
        student = getEntityManager().getReference(Students.class, student.getId());
        
        
        
    }
    
    public void updateStudent(){
        
    }
}

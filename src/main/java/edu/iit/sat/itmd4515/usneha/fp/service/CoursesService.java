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

/**
 *
 * @author snehaupadhyay
 */
@Named
@Stateless
public class CoursesService extends AbstractService<Courses>{

    public CoursesService() {
        super(Courses.class);
    }

    @Override
    public List<Courses> findAll() {
        return getEntityManager().createNamedQuery("Courses.findAll",
                Courses.class).getResultList();
    }
    

            
    public Courses findByName(String courseName) {    
        return getEntityManager().createNamedQuery("Courses.findByName",Courses.class).setParameter("courseName", courseName).getSingleResult();
    }
    
}

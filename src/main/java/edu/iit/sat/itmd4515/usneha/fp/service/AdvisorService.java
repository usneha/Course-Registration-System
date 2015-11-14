/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.service;

import edu.iit.sat.itmd4515.usneha.fp.domain.Advisor;
import edu.iit.sat.itmd4515.usneha.fp.domain.Courses;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author snehaupadhyay
 */
@Named
@Stateless
public class AdvisorService extends AbstractService<Advisor>{
    
    public AdvisorService(){
        super(Advisor.class);
    }

    public Advisor findByUsername(String username){
        return getEntityManager().createNamedQuery("Advisor.findByUsername",
                Advisor.class)
                .setParameter("username",username)
                .getSingleResult();
    }
    @Override
    public List<Advisor> findAll() {
        return getEntityManager().createNamedQuery("Advisor.findAll",
                Advisor.class).getResultList();
    }
    
}

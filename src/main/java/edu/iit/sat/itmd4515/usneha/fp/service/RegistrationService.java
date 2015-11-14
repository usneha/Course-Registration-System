/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.service;

import edu.iit.sat.itmd4515.usneha.fp.domain.Registration;
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
public class RegistrationService extends AbstractService<Registration>{

    public RegistrationService() {
        super(Registration.class);
    }

    @Override
    public List<Registration> findAll() {
        return getEntityManager().createNamedQuery("Registration.findAll",
                Registration.class).getResultList();
    }
    
}

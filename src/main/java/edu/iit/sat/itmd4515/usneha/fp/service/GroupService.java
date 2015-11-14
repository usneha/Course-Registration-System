/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.service;

import edu.iit.sat.itmd4515.usneha.fp.security.Groups;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author snehaupadhyay
 */

@Stateless
public class GroupService extends AbstractService<Groups> {

    public GroupService() {
        super(Groups.class);
    }

    @Override
    public List<Groups> findAll() {
        return getEntityManager().createNamedQuery("Groups.findAll",
                Groups.class).getResultList();
    }
    
}

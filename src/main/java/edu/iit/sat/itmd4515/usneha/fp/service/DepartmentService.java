/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.service;

import edu.iit.sat.itmd4515.usneha.fp.domain.Courses;
import edu.iit.sat.itmd4515.usneha.fp.domain.Department;
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
public class DepartmentService extends AbstractService<Department> {

    public DepartmentService() {
        super(Department.class);
    }

    @Override
    public List<Department> findAll() {
        return getEntityManager().createNamedQuery("Department.findAll",
                Department.class).getResultList();
    }
    
    public Department findByName(String departmentName) {    
        return getEntityManager().createNamedQuery("Department.findByName",Department.class).setParameter("departmentName", departmentName).getSingleResult();
    }
}

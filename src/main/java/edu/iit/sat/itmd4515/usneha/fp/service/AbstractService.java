/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static jdk.nashorn.internal.codegen.Compiler.LOG;

/**
 *
 * @author snehaupadhyay
 */
public abstract class AbstractService<T> {
    
    @PersistenceContext(unitName = "usnehaPU")
    private EntityManager em;

    
        private final Class<T> entityClass;
        
        protected AbstractService(Class entityClass){
            this.entityClass = entityClass;
        }
        
        protected EntityManager getEntityManager(){
            return this.em;
        }
        
        public Class<T> getEntityClass() {
            return entityClass;

           }
//        public void setEntityClass(Class<T> entityClass) {
//            this.entityClass = entityClass;
//        }

        public void create(T entity){
            em.persist(entity);
        }
        
        public T find(Object id){
            return em.find(entityClass,id);
        }

        public abstract List<T> findAll();
        
        public void update(T entity){
            em.merge(entity);
        }
        
        public void delete(T entity){
            em.remove(em.merge(entity));
        }
        
        public void deleteById(Object id){
            em.remove(em.getReference(entityClass, id));
        }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.web;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author snehaupadhyay
 */
public class AbstractJSFBean {
    
    protected FacesContext facesContext;
    protected Flash flash;
    protected AbstractJSFBean() {
    }
    
      @PostConstruct
      protected void postContruct() {
        facesContext = FacesContext.getCurrentInstance();
        flash = facesContext.getExternalContext().getFlash();
      }
}

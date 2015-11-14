/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.web;

import edu.iit.sat.itmd4515.usneha.fp.domain.Advisor;
import edu.iit.sat.itmd4515.usneha.fp.service.AdvisorService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author snehaupadhyay
 */
@WebServlet(name = "AdvisorPortalTestServlet", urlPatterns = {"/advisorPortal","/advisorPortal/"})
public class AdvisorPortalTestServlet extends HttpServlet {
    @EJB
    private AdvisorService advisorService;

    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><br> <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\">");
            out.println("<style> body{background-color:cyan;}</style>");
            out.println("<title>Servlet AdvisorPortalTestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdvisorPortalTestServlet at " + request.getContextPath() + "</h1>");
            
            if(request.isUserInRole("Amber")){
                Advisor a = advisorService.findByUsername(request.getRemoteUser());
                out.println("<h3>Please find your details below:</h3>");
                out.println("<h2>" +"First Name:"+" "+ a.getAdvisorFName() + 
                        "<br> " + "Last Name: " + a.getAdvisorLName() +
                        "<br> "+"Email Address: " + a.getAdvisorEmail() +
                        "<br>"+"Department: " + a.getDepartment().getDepartmentName()+ "</h2>");
            }
            
            out.println("<a href=\"" + request.getContextPath() + "/logout\">Logout</a>");
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

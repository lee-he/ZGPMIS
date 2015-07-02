/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import web.model.Project;
import web.model.ProjectJpaController;
import web.model.exceptions.NonexistentEntityException;

/**
 *
 * @author User
 */
@WebServlet(name = "project", urlPatterns = {"/project"})
public class project extends HttpServlet {

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
        response.setContentType("text/javascript;charset=UTF-8");
        String oper = request.getParameter("oper");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String duedate = request.getParameter("duedate");
        String budget = request.getParameter("budget");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        ProjectJpaController projectJpaController = new ProjectJpaController();
        try (PrintWriter out = response.getWriter()) {
            switch (oper) {
                case "list":
                    String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数  
                    String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数   
                    int totalRecord = projectJpaController.getProjectCount();
                    int totalPage = totalRecord % Integer.parseInt(rows) == 0 ? (totalRecord / Integer.parseInt(rows)) : (totalRecord / Integer.parseInt(rows) + 1);
                    Map map = new HashMap();
                    map.put("total", totalPage);
                    map.put("page", page);
                    map.put("records", totalRecord);
                    int index = (Integer.parseInt(page) - 1) * Integer.parseInt(rows);
                    map.put("rows", projectJpaController.list(Integer.parseInt(rows), index));
                    JSONObject.writeJSONString(map, response.getWriter());
                    break;
                case "del":
                    try {
                        projectJpaController.destroy(id);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(project.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "add":
                    Project addProject = new Project(name);
                    addProject.setAddress(address);
                    addProject.setBudget(Integer.parseInt(budget));
                    addProject.setDuedate(Date.valueOf(duedate));
                    addProject.setOwner(owner);
                    addProject.setTel(tel);
                    try {
                        projectJpaController.create(addProject);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(project.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(project.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }

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

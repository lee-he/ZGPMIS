/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import web.model.ProjectJpaController;

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
        try (PrintWriter out = response.getWriter()) {
            String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数  
            String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
            ProjectJpaController projectJpaController = new ProjectJpaController();
            int totalRecord = projectJpaController.getProjectCount(); // 总记录数(应根据数据库取得，在此只是模拟)  
            int totalPage = totalRecord % Integer.parseInt(rows) == 0 ? (totalRecord / Integer.parseInt(rows)) : (totalRecord / Integer.parseInt(rows) + 1); // 计算总页数  
            Map map = new HashMap();
            map.put("total", totalPage);
            map.put("page", page);
            map.put("records", totalRecord);
            int index = (Integer.parseInt(page) - 1) * Integer.parseInt(rows); // 开始记录数
            map.put("rows", projectJpaController.list(Integer.parseInt(rows), index));
            JSONObject.writeJSONString(map, response.getWriter());
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

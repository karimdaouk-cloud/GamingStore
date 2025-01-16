package com.gaming.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gaming.dao.UserDAO;

public class AdminUserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        
        try {
            switch (action) {
                case "delete":
                    userDAO.deleteUser(username);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("admin.jsp");
    }
}
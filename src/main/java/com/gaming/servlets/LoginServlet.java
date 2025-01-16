package com.gaming.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.gaming.beans.User;
import com.gaming.dao.UserDAO;

public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            User user = userDAO.findByUsername(username);
            if (user != null && password.equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                // Redirect based on user role
                if ("admin".equals(username)) {
                    response.sendRedirect("admin.jsp");
                } else {
                    response.sendRedirect("products.jsp");
                }
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Database Error: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
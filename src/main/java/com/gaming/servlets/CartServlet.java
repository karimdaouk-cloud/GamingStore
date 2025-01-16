package com.gaming.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.gaming.beans.User;
import com.gaming.dao.CartItemDAO;

public class CartServlet extends HttpServlet {
    private CartItemDAO cartItemDAO = new CartItemDAO();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        System.out.println("Cart action: " + action); // Debug log
        
        try {
            if ("add".equals(action)) {
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = 1;
                if (request.getParameter("quantity") != null) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }
                System.out.println("Adding to cart - Product ID: " + productId + ", Quantity: " + quantity); // Debug log
                cartItemDAO.addToCart(user.getId(), productId, quantity);
            }
            else if ("update".equals(action)) {
                int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                cartItemDAO.updateQuantity(cartItemId, quantity);
            }
            else if ("remove".equals(action)) {
                int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
                cartItemDAO.removeFromCart(cartItemId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error processing cart operation: " + e.getMessage());
        }
        
        response.sendRedirect("cart.jsp");
    }
}
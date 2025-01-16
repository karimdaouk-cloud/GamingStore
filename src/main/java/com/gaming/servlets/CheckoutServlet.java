package com.gaming.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.gaming.beans.*;
import com.gaming.dao.*;

public class CheckoutServlet extends HttpServlet {
    private CartItemDAO cartItemDAO = new CartItemDAO();
    private OrderDAO orderDAO = new OrderDAO();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            // Get cart items
            List<CartItem> cartItems = cartItemDAO.getCartItems(user.getId());
            
            if (cartItems.isEmpty()) {
                request.setAttribute("error", "Your cart is empty!");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
                return;
            }
            
            // Create order
            Order order = orderDAO.createOrder(user.getId(), cartItems);
            
            // Clear the cart
            cartItemDAO.clearCart(user.getId());
            
            // Store order ID in session for confirmation page
            session.setAttribute("lastOrderId", order.getId());
            
            // Redirect to confirmation page
            response.sendRedirect("orderConfirmation.jsp");
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error processing your order: " + e.getMessage());
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }
}
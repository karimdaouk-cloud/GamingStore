package com.gaming.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gaming.beans.Product;
import com.gaming.dao.ProductDAO;

public class AdminProductServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        try {
            switch (action) {
                case "add":
                    addProduct(request);
                    break;
                case "edit":
                    editProduct(request);
                    break;
                case "delete":
                    deleteProduct(request);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("admin.jsp");
    }
    
    private void addProduct(HttpServletRequest request) throws Exception {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setStockQuantity(stockQuantity);
        
        productDAO.addProduct(product);
    }
    
    private void editProduct(HttpServletRequest request) throws Exception {
        int id = Integer.parseInt(request.getParameter("productId"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setStockQuantity(stockQuantity);
        
        productDAO.updateProduct(product);
    }
    
    private void deleteProduct(HttpServletRequest request) throws Exception {
        int id = Integer.parseInt(request.getParameter("productId"));
        productDAO.deleteProduct(id);
    }
}
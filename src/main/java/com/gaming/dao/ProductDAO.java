package com.gaming.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.gaming.beans.Product;
import com.gaming.util.DBUtil;

public class ProductDAO {
    
    // Get all products
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                products.add(product);
            }
        }
        return products;
    }
    
    // Get product by ID
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDescription(rs.getString("description"));
                    product.setStockQuantity(rs.getInt("stock_quantity"));
                    return product;
                }
            }
        }
        return null;
    }
    
    // Add new product
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, description, stock_quantity) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getStockQuantity());
            
            System.out.println("Executing addProduct with values: " + product.getName() + ", " + product.getPrice());
            ps.executeUpdate();
        }
    }
    
    // Update existing product
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, description = ?, stock_quantity = ? WHERE id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getStockQuantity());
            ps.setInt(5, product.getId());
            
            System.out.println("Executing updateProduct for ID: " + product.getId());
            ps.executeUpdate();
        }
    }
    
    // Delete product
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            System.out.println("Executing deleteProduct for ID: " + id);
            ps.executeUpdate();
        }
    }
}
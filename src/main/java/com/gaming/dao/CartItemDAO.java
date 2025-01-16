package com.gaming.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.gaming.beans.CartItem;
import com.gaming.beans.Product;
import com.gaming.util.DBUtil;

public class CartItemDAO {
    private ProductDAO productDAO = new ProductDAO();

    // Add or update item in cart
    public void addToCart(int userId, int productId, int quantity) throws SQLException {
        // First check if item already exists in cart
        String checkSql = "SELECT id, quantity FROM cart_items WHERE user_id = ? AND product_id = ?";
        String updateSql = "UPDATE cart_items SET quantity = ? WHERE id = ?";
        String insertSql = "INSERT INTO cart_items (user_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
            
            checkPs.setInt(1, userId);
            checkPs.setInt(2, productId);

            ResultSet rs = checkPs.executeQuery();
            if (rs.next()) {
                // Update existing item
                try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                    int newQuantity = rs.getInt("quantity") + quantity;
                    updatePs.setInt(1, newQuantity);
                    updatePs.setInt(2, rs.getInt("id"));
                    updatePs.executeUpdate();
                }
            } else {
                // Insert new item
                try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
                    insertPs.setInt(1, userId);
                    insertPs.setInt(2, productId);
                    insertPs.setInt(3, quantity);
                    insertPs.executeUpdate();
                }
            }
        }
    }

    // Get all items in user's cart
    public List<CartItem> getCartItems(int userId) throws SQLException {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT * FROM cart_items WHERE user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("id"));
                item.setUserId(userId);
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setDateAdded(rs.getTimestamp("date_added"));
                
                // Get product details
                Product product = productDAO.getProductById(item.getProductId());
                item.setProduct(product);
                
                items.add(item);
            }
        }
        return items;
    }

    // Update item quantity
    public void updateQuantity(int cartItemId, int quantity) throws SQLException {
        String sql = "UPDATE cart_items SET quantity = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, quantity);
            ps.setInt(2, cartItemId);
            ps.executeUpdate();
        }
    }

    // Remove item from cart
    public void removeFromCart(int cartItemId) throws SQLException {
        String sql = "DELETE FROM cart_items WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cartItemId);
            ps.executeUpdate();
        }
    }

    // Clear user's cart
    public void clearCart(int userId) throws SQLException {
        String sql = "DELETE FROM cart_items WHERE user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
    }

    // Get cart total
    public double getCartTotal(int userId) throws SQLException {
        String sql = "SELECT SUM(p.price * ci.quantity) as total " +
                    "FROM cart_items ci " +
                    "JOIN products p ON ci.product_id = p.id " +
                    "WHERE ci.user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        return 0.0;
    }
}
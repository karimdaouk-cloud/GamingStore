package com.gaming.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.gaming.beans.User;
import com.gaming.util.DBUtil;

public class UserDAO {
    
	public User findByUsername(String username) throws SQLException {
	    String sql = "SELECT * FROM users WHERE username = ?";
	    
	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setString(1, username);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                User user = new User();
	                user.setId(rs.getInt("id")); // Make sure this line exists
	                user.setUsername(rs.getString("username"));
	                user.setPassword(rs.getString("password"));
	                user.setEmail(rs.getString("email"));
	                return user;
	            }
	        }
	    }
	    return null;
	}
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT username, email FROM users";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        }
        return users;
    }
    
    public void saveUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        }
    }
    
    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ? AND username != 'admin'";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            System.out.println("Deleting user: " + username);
            ps.executeUpdate();
        }
    }
}
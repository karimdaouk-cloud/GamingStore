<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gaming.beans.User"%>
<%@ page import="com.gaming.dao.ProductDAO"%>
<%@ page import="com.gaming.dao.UserDAO"%>
<%@ page import="com.gaming.beans.Product"%>
<%@ page import="java.util.List"%>
<%
User user = (User) session.getAttribute("user");
if (user == null || !"admin".equals(user.getUsername())) {
    response.sendRedirect("login.jsp");
    return;
}

ProductDAO productDAO = new ProductDAO();
UserDAO userDAO = new UserDAO();
List<Product> products = productDAO.getAllProducts();
List<User> users = userDAO.getAllUsers();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <div class="admin-container">
        <div class="admin-header">
            <h2>Admin Dashboard</h2>
            <a href="logout" class="logout-btn">Logout</a>
        </div>

        <!-- Product Management Section -->
        <div class="admin-section">
            <h3>Product Management</h3>
            <button onclick="showAddProductForm()" class="action-btn">Add New Product</button>
            
            <!-- Add Product Form -->
            <div id="addProductForm" style="display:none;" class="admin-form">
                <h4>Add New Product</h4>
                <form action="adminProduct" method="post">
                    <input type="hidden" name="action" value="add">
                    <div class="form-group">
                        <label>Name:</label>
                        <input type="text" name="name" required>
                    </div>
                    <div class="form-group">
                        <label>Price:</label>
                        <input type="number" step="0.01" name="price" required>
                    </div>
                    <div class="form-group">
                        <label>Description:</label>
                        <textarea name="description" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>Stock:</label>
                        <input type="number" name="stockQuantity" required>
                    </div>
                    <button type="submit" class="action-btn">Add Product</button>
                </form>
            </div>

            <!-- Edit Product Form -->
            <div id="editProductForm" style="display:none;" class="admin-form">
                <h4>Edit Product</h4>
                <form action="adminProduct" method="post">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="productId" id="editProductId">
                    <div class="form-group">
                        <label>Name:</label>
                        <input type="text" name="name" id="editName" required>
                    </div>
                    <div class="form-group">
                        <label>Price:</label>
                        <input type="number" step="0.01" name="price" id="editPrice" required>
                    </div>
                    <div class="form-group">
                        <label>Description:</label>
                        <textarea name="description" id="editDescription" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>Stock:</label>
                        <input type="number" name="stockQuantity" id="editStockQuantity" required>
                    </div>
                    <button type="submit" class="action-btn">Update Product</button>
                </form>
            </div>

            <!-- Products Table -->
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (products != null) {
                        for (Product product : products) { %>
                            <tr>
                                <td><%= product.getId() %></td>
                                <td><%= product.getName() %></td>
                                <td>$<%= String.format("%.2f", product.getPrice()) %></td>
                                <td><%= product.getStockQuantity() %></td>
                                <td>
                                    <button onclick="showEditForm('<%= product.getId() %>', '<%= product.getName() %>', '<%= product.getPrice() %>', '<%= product.getDescription() %>', '<%= product.getStockQuantity() %>')" class="edit-btn">Edit</button>
                                    <form action="adminProduct" method="post" style="display:inline;">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="productId" value="<%= product.getId() %>">
                                        <button type="submit" class="delete-btn">Delete</button>
                                    </form>
                                </td>
                            </tr>
                    <% }
                    } %>
                </tbody>
            </table>
        </div>

        <!-- User Management Section -->
        <div class="admin-section">
            <h3>User Management</h3>
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (users != null) {
                        for (User u : users) {
                            if (!"admin".equals(u.getUsername())) { %>
                                <tr>
                                    <td><%= u.getUsername() %></td>
                                    <td><%= u.getEmail() %></td>
                                    <td>
                                        <form action="adminUser" method="post" style="display:inline;">
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="username" value="<%= u.getUsername() %>">
                                            <button type="submit" class="delete-btn">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                    <%      }
                        }
                    } %>
                </tbody>
            </table>
        </div>
    </div>

    <script>
    function showAddProductForm() {
        document.getElementById('addProductForm').style.display = 'block';
        document.getElementById('editProductForm').style.display = 'none';
    }

    function showEditForm(id, name, price, description, stock) {
        document.getElementById('editProductForm').style.display = 'block';
        document.getElementById('addProductForm').style.display = 'none';
        
        document.getElementById('editProductId').value = id;
        document.getElementById('editName').value = name;
        document.getElementById('editPrice').value = price;
        document.getElementById('editDescription').value = description;
        document.getElementById('editStockQuantity').value = stock;
    }
    </script>
</body>
</html>
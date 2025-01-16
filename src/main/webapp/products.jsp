<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gaming.beans.*"%>
<%@ page import="com.gaming.dao.*"%>
<%@ page import="java.util.List"%>
<%
User user = (User) session.getAttribute("user");
if (user == null) {
    response.sendRedirect("login.jsp");
    return;
}

ProductDAO productDAO = new ProductDAO();
CartItemDAO cartItemDAO = new CartItemDAO();
List<Product> products = productDAO.getAllProducts();
List<CartItem> cartItems = cartItemDAO.getCartItems(user.getId());
%>
<!DOCTYPE html>
<html>
<head>
    <title>Gaming Store - Products</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <div class="header">
        <div class="nav-container">
            <a href="products.jsp" class="logo">Gaming Store</a>
            <div class="nav-right">
                <a href="cart.jsp" class="cart-icon">
                    <i class="fas fa-shopping-cart"></i>
                    <span class="cart-count"><%= cartItems.size() %></span>
                </a>
                <a href="logout" class="logout-btn">Logout</a>
            </div>
        </div>
    </div>

    <div class="products-container">
        <div class="products-grid">
            <% for(Product product : products) { %>
                <div class="product-card">
                    <h3><%= product.getName() %></h3>
                    <p><%= product.getDescription() %></p>
                    <p class="price">$<%= String.format("%.2f", product.getPrice()) %></p>
                    <% if(product.getStockQuantity() > 0) { %>
                       <form action="cart" method="post">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="productId" value="<%= product.getId() %>">
    <div class="quantity-input">
        <select name="quantity">
            <% for(int i = 1; i <= Math.min(10, product.getStockQuantity()); i++) { %>
                <option value="<%= i %>"><%= i %></option>
            <% } %>
        </select>
    </div>
    <button type="submit">Add to Cart</button>
</form>
                    <% } else { %>
                        <p class="out-of-stock">Out of Stock</p>
                    <% } %>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>
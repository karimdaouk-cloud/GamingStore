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

OrderDAO orderDAO = new OrderDAO();
List<Order> orders = orderDAO.getUserOrders(user.getId());
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Orders</title>
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
                </a>
                <a href="logout" class="logout-btn">Logout</a>
            </div>
        </div>
    </div>

    <div class="orders-container">
        <h2>My Orders</h2>
        
        <% if (orders.isEmpty()) { %>
            <div class="no-orders">
                <p>You haven't placed any orders yet.</p>
                <a href="products.jsp" class="continue-shopping-btn">Start Shopping</a>
            </div>
        <% } else { %>
            <div class="orders-list">
                <% for (Order order : orders) { %>
                    <div class="order-card">
                        <div class="order-header">
                            <div class="order-info">
                                <h3>Order #<%= order.getId() %></h3>
                                <p>Date: <%= order.getOrderDate() %></p>
                                <span class="order-status <%= order.getStatus().toLowerCase() %>">
                                    <%= order.getStatus() %>
                                </span>
                            </div>
                            <div class="order-total">
                                Total: $<%= String.format("%.2f", order.getTotalAmount()) %>
                            </div>
                        </div>
                        
                        <div class="order-items">
                            <% for (OrderItem item : order.getItems()) { %>
                                <div class="order-item">
                                    <div class="item-info">
                                        <span class="item-name"><%= item.getProduct().getName() %></span>
                                        <span class="item-quantity">Quantity: <%= item.getQuantity() %></span>
                                    </div>
                                    <span class="item-price">
                                        $<%= String.format("%.2f", item.getPrice() * item.getQuantity()) %>
                                    </span>
                                </div>
                            <% } %>
                        </div>
                    </div>
                <% } %>
            </div>
        <% } %>
    </div>
</body>
</html>
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

Integer lastOrderId = (Integer) session.getAttribute("lastOrderId");
if (lastOrderId == null) {
    response.sendRedirect("products.jsp");
    return;
}

OrderDAO orderDAO = new OrderDAO();
Order order = orderDAO.getUserOrders(user.getId()).stream()
    .filter(o -> o.getId() == lastOrderId)
    .findFirst()
    .orElse(null);

// Clear the last order ID from session
session.removeAttribute("lastOrderId");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Order Confirmation</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <div class="header">
        <div class="nav-container">
            <a href="products.jsp" class="logo">Gaming Store</a>
            <div class="nav-right">
                <a href="orders.jsp">My Orders</a>
                <a href="logout" class="logout-btn">Logout</a>
            </div>
        </div>
    </div>

    <div class="confirmation-container">
        <div class="confirmation-header">
            <i class="fas fa-check-circle"></i>
            <h2>Order Confirmed!</h2>
            <p>Thank you for your purchase</p>
        </div>

        <% if (order != null) { %>
            <div class="order-details">
                <h3>Order Details</h3>
                <p>Order Number: #<%= order.getId() %></p>
                <p>Order Date: <%= order.getOrderDate() %></p>
                <p>Status: <%= order.getStatus() %></p>

                <div class="order-items">
                    <h4>Items Ordered</h4>
                    <% for (OrderItem item : order.getItems()) { %>
                        <div class="order-item">
                            <div class="item-info">
                                <span class="item-name"><%= item.getProduct().getName() %></span>
                                <span class="item-quantity">Quantity: <%= item.getQuantity() %></span>
                            </div>
                            <span class="item-price">$<%= String.format("%.2f", item.getPrice() * item.getQuantity()) %></span>
                        </div>
                    <% } %>
                </div>

                <div class="order-total">
                    <p>Total Amount: $<%= String.format("%.2f", order.getTotalAmount()) %></p>
                </div>
            </div>
        <% } %>

        <div class="confirmation-actions">
            <a href="products.jsp" class="continue-shopping-btn">Continue Shopping</a>
            <a href="orders.jsp" class="view-orders-btn">View My Orders</a>
        </div>
    </div>
</body>
</html>
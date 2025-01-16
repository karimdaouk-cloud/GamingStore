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

CartItemDAO cartItemDAO = new CartItemDAO();
List<CartItem> cartItems = cartItemDAO.getCartItems(user.getId());
double cartTotal = cartItemDAO.getCartTotal(user.getId());
%>
<!DOCTYPE html>
<html>
<head>
    <title>Gaming Store - Cart</title>
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

    <div class="cart-container">
        <h2>Shopping Cart</h2>
        
        <% if (cartItems.isEmpty()) { %>
            <div class="empty-cart">
                <p>Your cart is empty</p>
                <a href="products.jsp" class="continue-shopping-btn">Continue Shopping</a>
            </div>
        <% } else { %>
            <div class="cart-items">
                <% for (CartItem item : cartItems) { %>
                    <div class="cart-item">
                        <div class="item-details">
                            <h3><%= item.getProduct().getName() %></h3>
                            <p class="item-price">$<%= String.format("%.2f", item.getProduct().getPrice()) %></p>
                        </div>
                        <div class="item-controls">
                            <form action="cart" method="post" class="quantity-form">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="cartItemId" value="<%= item.getId() %>">
                                <div class="quantity-control">
                                    <button type="button" onclick="decrementQuantity(this)" class="qty-btn">-</button>
                                    <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" max="99" class="quantity-input" onchange="this.form.submit()">
                                    <button type="button" onclick="incrementQuantity(this)" class="qty-btn">+</button>
                                </div>
                            </form>
                            <form action="cart" method="post" class="remove-form">
                                <input type="hidden" name="action" value="remove">
                                <input type="hidden" name="cartItemId" value="<%= item.getId() %>">
                                <button type="submit" class="remove-btn">Remove</button>
                            </form>
                        </div>
                        <div class="item-total">
                            $<%= String.format("%.2f", item.getProduct().getPrice() * item.getQuantity()) %>
                        </div>
                    </div>
                <% } %>
            </div>

            <div class="cart-summary">
                <div class="summary-item">
                    <span>Subtotal:</span>
                    <span>$<%= String.format("%.2f", cartTotal) %></span>
                </div>
                <div class="summary-item">
                    <span>Tax (13%):</span>
                    <span>$<%= String.format("%.2f", cartTotal * 0.13) %></span>
                </div>
                <div class="summary-item total">
                    <span>Total:</span>
                    <span>$<%= String.format("%.2f", cartTotal * 1.13) %></span>
                </div>
            </div>

            <div class="cart-actions">
                <a href="products.jsp" class="continue-shopping-btn">Continue Shopping</a>
                <form action="checkout" method="post" class="checkout-form">
                    <button type="submit" class="checkout-btn">Proceed to Checkout</button>
                </form>
            </div>
        <% } %>
    </div>

    <script>
        function incrementQuantity(button) {
            var input = button.parentNode.querySelector('input[type=number]');
            if (input.value < 99) {
                input.value = parseInt(input.value) + 1;
                input.form.submit();
            }
        }

        function decrementQuantity(button) {
            var input = button.parentNode.querySelector('input[type=number]');
            if (input.value > 1) {
                input.value = parseInt(input.value) - 1;
                input.form.submit();
            }
        }
    </script>
</body>
</html>
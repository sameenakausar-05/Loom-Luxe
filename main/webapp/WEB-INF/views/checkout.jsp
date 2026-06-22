<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.loomandluxe.model.CartItem" %>
<%@ page import="com.loomandluxe.model.User" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Checkout</title>

    <!-- CSS -->

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/styles.css">

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/checkout.css">

</head>

<body>

    <!-- NAVBAR -->

    <jsp:include page="partials/navbar.jsp" />

    <%
        User user =
                (User) session.getAttribute("loggedInUser");

        List<CartItem> cartItems =
                (List<CartItem>) request.getAttribute("cartItems");

        Double total =
                (Double) request.getAttribute("cartTotal");
    %>

    <div class="checkout-container">

        <h1>Checkout</h1>

        <div class="checkout-grid">

            <!-- DELIVERY FORM -->

            <div class="checkout-form">

                <h2>Delivery Address</h2>

                <form action="<%= request.getContextPath() %>/place-order"
                      method="post">

                    <input type="text"
                           name="fullName"
                           value="<%= user.getFullName() %>"
                           required>

                    <input type="text"
                           name="phone"
                           value="<%= user.getPhone() %>"
                           required>

                    <input type="text"
                           name="addressLine1"
                           value="<%= user.getAddressLine1() %>"
                           required>

                    <input type="text"
                           name="addressLine2"
                           value="<%= user.getAddressLine2() %>">

                    <input type="text"
                           name="city"
                           value="<%= user.getCity() %>"
                           required>

                    <input type="text"
                           name="state"
                           value="<%= user.getState() %>"
                           required>

                    <input type="text"
                           name="country"
                           value="<%= user.getCountry() %>"
                           required>

                    <input type="text"
                           name="pincode"
                           value="<%= user.getPincode() %>"
                           required>

                    <!-- PAYMENT -->

                    <select name="paymentMethod"
                            required>

                        <option value="COD">

                            Cash On Delivery

                        </option>

                        <option value="CARD">

                            Credit/Debit Card

                        </option>

                    </select>

                    <button type="submit">

                        Place Order

                    </button>

                </form>

            </div>

            <!-- ORDER SUMMARY -->

            <div class="order-summary">

                <h2>Order Summary</h2>

                <%
                    for(CartItem item : cartItems){
                %>

                <div class="summary-item">

                    <img src="<%= request.getContextPath() %>/<%= item.getImageUrl() %>"
                         alt="Product">

                    <div>

                        <h3>
                            <%= item.getProductName() %>
                        </h3>

                        <p>
                            Size :
                            <%= item.getSize() %>
                        </p>

                        <p>
                            Qty :
                            <%= item.getQuantity() %>
                        </p>

                    </div>

                    <h4>

                        ₹ <%= item.getSubtotal() %>

                    </h4>

                </div>

                <%
                    }
                %>

                <div class="summary-total">

                    <h2>

                        Total :
                        ₹ <%= total %>

                    </h2>

                </div>

            </div>

        </div>

    </div>

    <!-- FOOTER -->

    <jsp:include page="partials/footer.jsp" />

</body>

</html>
<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.loomandluxe.model.CartItem" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Shopping Cart</title>

    <!-- CSS -->
    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/styles.css">

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/cart.css">

</head>

<body>

    <!-- NAVBAR -->
    <jsp:include page="partials/navbar.jsp" />

    <div class="cart-container">

        <h1>Your Shopping Cart</h1>

        <%
            List<CartItem> cartItems =
                    (List<CartItem>) request.getAttribute("cartItems");

            Double cartTotal =
                    (Double) request.getAttribute("cartTotal");
        %>

        <%
            if(cartItems == null || cartItems.isEmpty()) {
        %>

            <div class="empty-cart">

                <h2>Your cart is empty</h2>

                <a href="<%= request.getContextPath() %>/products"
                   class="shop-btn">

                    Continue Shopping

                </a>

            </div>

        <%
            } else {
        %>

        <!-- CART ITEMS -->

        <div class="cart-items">

            <%
                for(CartItem item : cartItems) {
            %>

            <div class="cart-card">

                <!-- IMAGE -->

                <div class="cart-image">

                    <img src="<%= item.getImageUrl() %>"
                         alt="Product Image">

                </div>

                <!-- DETAILS -->

                <div class="cart-details">

                    <h2>
                        <%= item.getProductName() %>
                    </h2>

                    <p>
                        Size :
                        <strong>
                            <%= item.getSize() %>
                        </strong>
                    </p>

                    <p>
                        Price :
                        ₹ <%= item.getPrice() %>
                    </p>

                    <!-- UPDATE QUANTITY -->

                    <form action="<%= request.getContextPath() %>/cart"
                          method="post">

                        <input type="hidden"
                               name="action"
                               value="update">

                        <input type="hidden"
                               name="productId"
                               value="<%= item.getProductId() %>">

                        <input type="hidden"
                               name="size"
                               value="<%= item.getSize() %>">

                        <label>Quantity :</label>

                        <input type="number"
                               name="quantity"
                               value="<%= item.getQuantity() %>"
                               min="1">

                        <button type="submit">

                            Update

                        </button>

                    </form>

                    <h3>

                        Subtotal :
                        ₹ <%= item.getSubtotal() %>

                    </h3>

                    <!-- REMOVE -->

                    <a href="<%= request.getContextPath() %>/cart?action=remove&productId=<%= item.getProductId() %>&size=<%= item.getSize() %>"
                       class="remove-btn">

                        Remove

                    </a>

                </div>

            </div>

            <%
                }
            %>

        </div>

        <!-- TOTAL -->

        <div class="cart-summary">

            <h2>

                Total :
                ₹ <%= cartTotal %>

            </h2>

            <a href="<%= request.getContextPath() %>/checkout"
   class="checkout-btn">

    Proceed To Checkout

</a>

        </div>

        <%
            }
        %>

    </div>

    <!-- FOOTER -->
    <jsp:include page="partials/footer.jsp" />

</body>

</html>
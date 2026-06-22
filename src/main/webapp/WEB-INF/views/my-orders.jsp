<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>My Orders</title>

    <!-- CSS -->

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/styles.css">

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/my-orders.css">

</head>

<body>

    <!-- NAVBAR -->

    <jsp:include page="partials/navbar.jsp" />

    <%
        List<Map<String, Object>> orders =
                (List<Map<String, Object>>)
                request.getAttribute("orders");
    %>

    <div class="orders-container">

        <h1>My Orders</h1>

        <%
            if(orders == null || orders.isEmpty()) {
        %>

        <!-- EMPTY -->

        <div class="empty-orders">

            <h2>

                No Orders Found

            </h2>

            <a href="<%= request.getContextPath() %>/products"
               class="shop-btn">

                Start Shopping

            </a>

        </div>

        <%
            }
            else{
        %>

        <!-- ORDERS -->

        <div class="orders-list">

            <%
                for(Map<String, Object> order : orders){
            %>

            <div class="order-card">

                <!-- IMAGE -->

                <div class="order-image">

                    <img src="<%= request.getContextPath() %>/<%= order.get("imageUrl") %>"
                         alt="Product">

                </div>

                <!-- DETAILS -->

                <div class="order-details">

                    <h2>

                        <%= order.get("productName") %>

                    </h2>

                    <p>

                        Order ID :
                        #<%= order.get("orderId") %>

                    </p>

                    <p>

                        Quantity :
                        <%= order.get("quantity") %>

                    </p>

                    <p>

                        Price :
                        ₹ <%= order.get("price") %>

                    </p>

                    <p>

                        Payment :
                        <%= order.get("paymentMethod") %>

                    </p>

                    <p>

                        Status :
                        <span class="status">

                            <%= order.get("orderStatus") %>

                        </span>

                    </p>

                    <h3>

                        Total :
                        ₹ <%= order.get("totalAmount") %>

                    </h3>

                </div>

            </div>

            <%
                }
            %>

        </div>

        <%
            }
        %>

    </div>

    <!-- FOOTER -->

    <jsp:include page="partials/footer.jsp" />

</body>

</html>
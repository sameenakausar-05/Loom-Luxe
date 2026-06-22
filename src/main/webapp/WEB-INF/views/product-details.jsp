<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.loomandluxe.model.Product" %>
<%@ page import="com.loomandluxe.model.ProductVariant" %>

<%
    Product product =
            (Product) request.getAttribute("product");

    List<ProductVariant> variants =
            (List<ProductVariant>) request.getAttribute("variants");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Product Details | Loom & Luxe</title>

    <!-- GLOBAL CSS -->
    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/styles.css">

    <!-- PRODUCT DETAILS CSS -->
    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/product-details.css">

</head>

<body>

    <!-- NAVBAR -->
    <%@ include file="partials/navbar.jsp" %>


    <!-- PRODUCT DETAILS -->
    <section class="product-details-page">

        <div class="product-details-container">

            <!-- PRODUCT IMAGE -->
            <div class="product-image">

                <img src="<%= product.getImageUrl() %>"
                     alt="Product Image">

            </div>


            <!-- PRODUCT INFO -->
            <div class="product-info">

                <!-- BRAND -->
                <div class="product-brand">

                    <%= product.getBrand() %>

                </div>

                <!-- PRODUCT NAME -->
                <h1 class="product-name">

                    <%= product.getProductName() %>

                </h1>

                <!-- PRICE -->
                <div class="product-price">

                    ₹ <%= product.getPrice() %>

                </div>

                <!-- DESCRIPTION -->
                <p class="product-description">

                    <%= product.getDescription() %>

                </p>


                <!-- VARIANTS -->
                <div class="variant-section">

                    <h3>Select Size</h3>

                    <div class="variant-list">

                        <%
                            if(variants != null){

                                for(ProductVariant variant : variants){
                        %>

                        <div class="variant-box
                            <%= variant.getStockQuantity() <= 0
                            ? "out-of-stock"
                            : "" %>">

                            <strong>
                                <%= variant.getSize() %>
                            </strong>

                            <br>

                            Stock:
                            <%= variant.getStockQuantity() %>

                        </div>

                        <%
                                }
                            }
                        %>

                    </div>

                </div>


                <!-- ADD TO CART FORM -->

<form action="<%= request.getContextPath() %>/cart"
      method="post"
      class="cart-form">

    <!-- ACTION -->

    <input type="hidden"
           name="action"
           value="add">

    <!-- PRODUCT ID -->

    <input type="hidden"
           name="productId"
           value="<%= product.getProductId() %>">

    <!-- SIZE -->

    <label>Select Size</label>

    <select name="size" required>

        <option value="S">S</option>

        <option value="M">M</option>

        <option value="L">L</option>

        <option value="XL">XL</option>

    </select>

    <!-- QUANTITY -->

    <label>Quantity</label>

    <input type="number"
           name="quantity"
           value="1"
           min="1">

    <!-- BUTTON -->

    <button type="submit"
            class="add-cart-btn">

        Add To Cart

    </button>

</form>

            </div>

        </div>

    </section>


    <!-- FOOTER -->
    <%@ include file="partials/footer.jsp" %>

</body>

</html>
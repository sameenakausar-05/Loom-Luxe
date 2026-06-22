<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.loomandluxe.model.Product" %>

<%
    List<Product> products =
            (List<Product>) request.getAttribute("products");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Loom & Luxe</title>

    <link rel="stylesheet"
      href="<%= request.getContextPath() %>/assets/css/styles.css">

<link rel="stylesheet"
      href="<%= request.getContextPath() %>/assets/css/home.css">

</head>

<body>

    <!-- NAVBAR -->
    <%@ include file="partials/navbar.jsp" %>


    <!-- HERO SECTION -->
    <section class="hero">

        <div class="hero-content">

            <h1>
                Elevate Your <span>Fashion</span> Style
            </h1>

            <p>
                Discover premium fashion collections with
                modern trends, elegant styles, and timeless
                designs only at Loom & Luxe.
            </p>

            <a href="products" class="hero-btn">
                Shop Now
            </a>

        </div>

        <div class="hero-image">

            <img src="https://images.unsplash.com/photo-1529139574466-a303027c1d8b"
                 alt="Fashion Banner">

        </div>

    </section>


    <!-- FEATURED PRODUCTS -->
    <section class="featured-section">

        <h2 class="section-title">
            Featured Products
        </h2>

        <div class="product-container">

            <%
                if(products != null){

                    for(Product product : products){
            %>

            <div class="product-card">

                <img src="<%= product.getImageUrl() %>"
                     alt="Product Image">

                <div class="product-info">

                    <h3>
                        <%= product.getProductName() %>
                    </h3>

                    <p>
                        <%= product.getBrand() %>
                    </p>

                    <div class="product-price">

                        ₹ <%= product.getPrice() %>

                    </div>

                </div>

            </div>

            <%
                    }
                }
            %>

        </div>

    </section>


    <!-- FOOTER -->
    <%@ include file="partials/footer.jsp" %>

</body>

</html>
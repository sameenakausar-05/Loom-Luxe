<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.loomandluxe.model.Product" %>
<%@ page import="com.loomandluxe.model.Category" %>

<%
    List<Product> products =
            (List<Product>) request.getAttribute("products");

    List<Category> categories =
            (List<Category>) request.getAttribute("categories");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Products | Loom & Luxe</title>

    <link rel="stylesheet"
      href="<%= request.getContextPath() %>/assets/css/styles.css">

<link rel="stylesheet"
      href="<%= request.getContextPath() %>/assets/css/products.css">

</head>

<body>

    <!-- NAVBAR -->
    <%@ include file="partials/navbar.jsp" %>


    <!-- PRODUCTS PAGE -->
    <section class="products-page">

        <!-- SIDEBAR -->
        <div class="filter-sidebar">

            <h2>Filters</h2>

            <!-- SEARCH -->
            <div class="filter-box">

                <h3>Search</h3>

                <input type="text"
                       placeholder="Search products">

            </div>

            <!-- CATEGORY FILTER -->
            <div class="filter-box">

    <h3>Categories</h3>

    <ul class="category-list">

        <li>
            <a href="<%= request.getContextPath() %>/products">
                All Products
            </a>
        </li>

        <%
            if(categories != null){

                for(Category category : categories){
        %>

        <li>

            <a href="<%= request.getContextPath() %>/products?categoryId=<%= category.getCategoryId() %>">

                <%= category.getCategoryName() %>

            </a>

        </li>

        <%
                }
            }
        %>

    </ul>

</div>

        </div>


        <!-- PRODUCTS SECTION -->
        <div class="products-content">

            <h1 class="products-title">
                Explore Fashion Collections
            </h1>

            <div class="products-grid">

                <%
                    if(products != null){

                        for(Product product : products){
                %>

                <div class="product-card">

                    <img src="<%= request.getContextPath() %>/<%= product.getImageUrl() %>"
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

                        <a href="<%= request.getContextPath() %>/product-details?id=<%= product.getProductId() %>"
   class="view-btn">

    View Details

</a>

                    </div>

                </div>

                <%
                        }
                    }
                %>

            </div>

        </div>

    </section>


    <!-- FOOTER -->
    <%@ include file="partials/footer.jsp" %>

</body>

</html>
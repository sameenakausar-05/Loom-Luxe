<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

</head>

<body>

    <nav class="navbar">

        <!-- LOGO -->
        <div class="logo">

            <a href="<%= request.getContextPath() %>/home">

                Loom & Luxe

            </a>

        </div>


        <!-- NAV LINKS -->
        <ul class="nav-links">

            <li>
                <a href="<%= request.getContextPath() %>/home">
                    Home
                </a>
            </li>

            <li>
    <a href="<%= request.getContextPath() %>/my-orders">

        My Orders

    </a>
</li>

            <li>
                <a href="#">
                    New Arrivals
                </a>
            </li>

            <li>
                <a href="#">
                    Men
                </a>
            </li>

            <li>
                <a href="#">
                    Women
                </a>
            </li>

        </ul>


        <!-- RIGHT SECTION -->
        <div class="nav-right">

    <!-- CART BUTTON -->
  <a href="<%= request.getContextPath() %>/cart"
   class="cart-btn">

    Cart

</a>

    <!-- LOGIN BUTTON -->
    <a href="<%= request.getContextPath() %>/login"
       class="login-btn">

        Login

    </a>

</div>

            </a>

        </div>

    </nav>

</body>

</html>
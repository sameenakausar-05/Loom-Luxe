<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Login | Loom & Luxe</title>

    <!-- GLOBAL CSS -->
    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/styles.css">

    <!-- AUTH CSS -->
    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/auth.css">

</head>

<body>

    <!-- NAVBAR -->
    <%@ include file="partials/navbar.jsp" %>


    <!-- LOGIN SECTION -->
    <section class="auth-section">

        <div class="auth-container">

            <h1>Welcome Back</h1>

            <p class="auth-subtitle">
                Login to continue shopping
            </p>

            <!-- ERROR MESSAGE -->
            <%
                String errorMessage =
                        (String) request.getAttribute("errorMessage");

                if(errorMessage != null){
            %>

            <div class="error-message">

                <%= errorMessage %>

            </div>

            <%
                }
            %>

            <!-- LOGIN FORM -->
            <form action="<%= request.getContextPath() %>/login"
                  method="post"
                  class="auth-form">

                <!-- EMAIL -->
                <div class="form-group">

                    <label>Email</label>

                    <input type="email"
                           name="email"
                           required>

                </div>

                <!-- PASSWORD -->
                <div class="form-group">

                    <label>Password</label>

                    <input type="password"
                           name="password"
                           required>

                </div>

                <!-- SUBMIT BUTTON -->
                <button type="submit"
                        class="auth-btn">

                    Login

                </button>

            </form>


            <!-- REGISTER LINK -->
            <div class="auth-footer">

                Don't have an account?

                <a href="<%= request.getContextPath() %>/register">

                    Register

                </a>

            </div>

        </div>

    </section>


    <!-- FOOTER -->
    <%@ include file="partials/footer.jsp" %>

</body>

</html>
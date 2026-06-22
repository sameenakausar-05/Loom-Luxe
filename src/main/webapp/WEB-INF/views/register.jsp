<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Register | Loom & Luxe</title>

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


    <!-- REGISTER SECTION -->
    <section class="auth-section">

        <div class="auth-container">

            <h1>Create Account</h1>

            <p class="auth-subtitle">
                Join Loom & Luxe today
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

            <!-- REGISTER FORM -->
            <form action="<%= request.getContextPath() %>/register"
                  method="post"
                  class="auth-form">

                <!-- FULL NAME -->
                <div class="form-group">

                    <label>Full Name</label>

                    <input type="text"
                           name="fullName"
                           required>

                </div>

                <!-- EMAIL -->
                <div class="form-group">

                    <label>Email</label>

                    <input type="email"
                           name="email"
                           required>

                </div>

                <!-- PHONE -->
                <div class="form-group">

                    <label>Phone</label>

                    <input type="text"
                           name="phone"
                           required>

                </div>

                <!-- PASSWORD -->
                <div class="form-group">

                    <label>Password</label>

                    <input type="password"
                           name="password"
                           required>

                </div>

                <!-- ADDRESS LINE 1 -->
                <div class="form-group">

                    <label>Address Line 1</label>

                    <input type="text"
                           name="addressLine1"
                           required>

                </div>

                <!-- ADDRESS LINE 2 -->
                <div class="form-group">

                    <label>Address Line 2</label>

                    <input type="text"
                           name="addressLine2">

                </div>

                <!-- CITY -->
                <div class="form-group">

                    <label>City</label>

                    <input type="text"
                           name="city"
                           required>

                </div>

                <!-- STATE -->
                <div class="form-group">

                    <label>State</label>

                    <input type="text"
                           name="state"
                           required>

                </div>

                <!-- COUNTRY -->
                <div class="form-group">

                    <label>Country</label>

                    <input type="text"
                           name="country"
                           required>

                </div>

                <!-- PINCODE -->
                <div class="form-group">

                    <label>Pincode</label>

                    <input type="text"
                           name="pincode"
                           required>

                </div>

                <!-- SUBMIT BUTTON -->
                <button type="submit"
                        class="auth-btn">

                    Register

                </button>

            </form>


            <!-- LOGIN LINK -->
            <div class="auth-footer">

                Already have an account?

                <a href="<%= request.getContextPath() %>/login">

                    Login

                </a>

            </div>

        </div>

    </section>


    <!-- FOOTER -->
    <%@ include file="partials/footer.jsp" %>

</body>

</html>
<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Order Confirmation</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/styles.css">
          
      <link rel="stylesheet"
      href="<%= request.getContextPath() %>/assets/css/order-confirmation.css">

    <style>

        .confirmation-container{

            min-height:70vh;

            display:flex;

            flex-direction:column;

            justify-content:center;

            align-items:center;

            text-align:center;
        }

        .confirmation-container h1{

            color:green;

            font-size:45px;

            margin-bottom:20px;
        }

        .confirmation-container p{

            font-size:20px;

            margin-bottom:15px;
        }

        .continue-btn{

            margin-top:25px;

            display:inline-block;

            padding:14px 30px;

            background:#ff5e78;

            color:white;

            text-decoration:none;

            border-radius:30px;
        }

    </style>

</head>

<body>

    <div class="confirmation-container">

        <!-- SUCCESS ICON -->

        <div class="success-icon">

            ✓

        </div>

        <!-- HEADING -->

        <h1>

            Order Placed Successfully!

        </h1>

        <!-- MESSAGE -->

        <p>

            Thank you for shopping with Loom & Luxe.

        </p>

        <p>

            Your order has been confirmed successfully.

        </p>

        <!-- ORDER ID -->

        <div class="order-id">

            Order ID :
            #<%= request.getAttribute("orderId") %>

        </div>

        <!-- BUTTON -->

        <a href="<%= request.getContextPath() %>/products"
           class="continue-btn">

            Continue Shopping

        </a>

    </div>

</body>
</html>
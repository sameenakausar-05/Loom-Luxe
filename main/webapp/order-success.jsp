<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Order Success</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/assets/css/styles.css">

    <style>

        .success-container{

            min-height:70vh;

            display:flex;

            flex-direction:column;

            justify-content:center;

            align-items:center;

            text-align:center;
        }

        .success-container h1{

            font-size:50px;

            color:green;

            margin-bottom:20px;
        }

        .success-container p{

            font-size:20px;

            margin-bottom:30px;
        }

        .continue-btn{

            display:inline-block;

            padding:15px 30px;

            background:#ff5e78;

            color:white;

            text-decoration:none;

            border-radius:30px;

            transition:0.3s;
        }

        .continue-btn:hover{

            background:#ff3f62;
        }

    </style>

</head>

<body>

    <div class="success-container">

        <h1>

            Order Placed Successfully!

        </h1>

        <p>

            Thank you for shopping with Loom & Luxe.

        </p>

        <a href="<%= request.getContextPath() %>/products"
           class="continue-btn">

            Continue Shopping

        </a>

    </div>

</body>

</html>
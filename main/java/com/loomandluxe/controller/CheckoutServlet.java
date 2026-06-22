package com.loomandluxe.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.loomandluxe.config.DBConnection;
import com.loomandluxe.model.CartItem;
import com.loomandluxe.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // =========================
    // SHOW CHECKOUT PAGE
    // =========================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // CHECK LOGIN

        User user =
                (User) session.getAttribute("loggedInUser");

        if(user == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        // GET CART

        List<CartItem> cartItems =
                (List<CartItem>) session.getAttribute("cart");

        if(cartItems == null || cartItems.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        // TOTAL

        double total = 0;

        for(CartItem item : cartItems) {

            total += item.getSubtotal();
        }

        request.setAttribute("cartItems", cartItems);

        request.setAttribute("cartTotal", total);

        request.getRequestDispatcher(
                "/WEB-INF/views/checkout.jsp")
                .forward(request, response);
    }

    // =========================
    // PLACE ORDER
    // =========================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user =
                (User) session.getAttribute("loggedInUser");

        List<CartItem> cartItems =
                (List<CartItem>) session.getAttribute("cart");

        if(user == null || cartItems == null || cartItems.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        Connection conn = null;

        PreparedStatement orderStmt = null;

        PreparedStatement itemStmt = null;

        ResultSet rs = null;

        try {

            conn = DBConnection.getConnection();

            // =========================
            // TOTAL
            // =========================

            double total = 0;

            for(CartItem item : cartItems) {

                total += item.getSubtotal();
            }

            // =========================
            // INSERT ORDER
            // =========================

            String orderQuery =

                    "INSERT INTO orders (" +
                    "user_id, " +
                    "total_amount, " +
                    "payment_method, " +
                    "delivery_name, " +
                    "delivery_phone, " +
                    "delivery_address_line1, " +
                    "delivery_address_line2, " +
                    "delivery_city, " +
                    "delivery_state, " +
                    "delivery_country, " +
                    "delivery_pincode" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            orderStmt = conn.prepareStatement(
                    orderQuery,
                    Statement.RETURN_GENERATED_KEYS
            );

            orderStmt.setInt(
                    1,
                    user.getUserId());

            orderStmt.setBigDecimal(
                    2,
                    BigDecimal.valueOf(total));

            orderStmt.setString(
                    3,
                    request.getParameter("paymentMethod"));

            orderStmt.setString(
                    4,
                    request.getParameter("fullName"));

            orderStmt.setString(
                    5,
                    request.getParameter("phone"));

            orderStmt.setString(
                    6,
                    request.getParameter("addressLine1"));

            orderStmt.setString(
                    7,
                    request.getParameter("addressLine2"));

            orderStmt.setString(
                    8,
                    request.getParameter("city"));

            orderStmt.setString(
                    9,
                    request.getParameter("state"));

            orderStmt.setString(
                    10,
                    request.getParameter("country"));

            orderStmt.setString(
                    11,
                    request.getParameter("pincode"));

            orderStmt.executeUpdate();

            // =========================
            // GET ORDER ID
            // =========================

            rs = orderStmt.getGeneratedKeys();

            int orderId = 0;

            if(rs.next()) {

                orderId = rs.getInt(1);
            }

            // =========================
            // INSERT ORDER ITEMS
            // =========================

            String itemQuery =

                    "INSERT INTO order_items (" +
                    "order_id, " +
                    "variant_id, " +
                    "quantity, " +
                    "price" +
                    ") VALUES (?, ?, ?, ?)";

            itemStmt = conn.prepareStatement(itemQuery);

            for(CartItem item : cartItems) {

                itemStmt.setInt(1, orderId);

                // TEMPORARY
                // USING PRODUCT ID AS VARIANT ID

                itemStmt.setInt(
                        2,
                        item.getProductId());

                itemStmt.setInt(
                        3,
                        item.getQuantity());

                itemStmt.setBigDecimal(
                        4,
                        BigDecimal.valueOf(item.getPrice()));

                itemStmt.executeUpdate();
            }

            // =========================
            // CLEAR CART
            // =========================

            session.removeAttribute("cart");

            // SUCCESS PAGE

            response.sendRedirect(
                    request.getContextPath()
                    + "/order-success.jsp");

        }
        catch(Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath() + "/checkout");
        }
    }
}
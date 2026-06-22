package com.loomandluxe.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loomandluxe.config.DBConnection;
import com.loomandluxe.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/my-orders")
public class MyOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // =========================
        // CHECK LOGIN
        // =========================

        User user =
                (User) session.getAttribute("loggedInUser");

        if(user == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet rs = null;

        // STORE ORDERS

        List<Map<String, Object>> orders =
                new ArrayList<>();

        try {

            conn = DBConnection.getConnection();

            // =========================
            // FETCH ORDERS + PRODUCTS
            // =========================

            String query =

                    "SELECT " +
                    "o.order_id, " +
                    "o.total_amount, " +
                    "o.order_status, " +
                    "o.payment_method, " +
                    "oi.quantity, " +
                    "oi.price, " +
                    "p.product_name, " +
                    "p.image_url " +
                    "FROM orders o " +
                    "JOIN order_items oi " +
                    "ON o.order_id = oi.order_id " +
                    "JOIN products p " +
                    "ON oi.variant_id = p.product_id " +
                    "WHERE o.user_id = ? " +
                    "ORDER BY o.order_id DESC";

            stmt = conn.prepareStatement(query);

            stmt.setInt(1, user.getUserId());

            rs = stmt.executeQuery();

            while(rs.next()) {

                Map<String, Object> order =
                        new HashMap<>();

                order.put(
                        "orderId",
                        rs.getInt("order_id"));

                order.put(
                        "totalAmount",
                        rs.getBigDecimal("total_amount"));

                order.put(
                        "orderStatus",
                        rs.getString("order_status"));

                order.put(
                        "paymentMethod",
                        rs.getString("payment_method"));

                order.put(
                        "quantity",
                        rs.getInt("quantity"));

                order.put(
                        "price",
                        rs.getBigDecimal("price"));

                order.put(
                        "productName",
                        rs.getString("product_name"));

                order.put(
                        "imageUrl",
                        rs.getString("image_url"));

                orders.add(order);
            }

            // SEND TO JSP

            request.setAttribute("orders", orders);

            request.getRequestDispatcher(
                    "/WEB-INF/views/my-orders.jsp")
                    .forward(request, response);

        }
        catch(Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath() + "/home");
        }
    }
}
package com.loomandluxe.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/order-confirmation")
public class OrderConfirmationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // GET ORDER ID

        String orderId =
                request.getParameter("orderId");

        // VALIDATION

        if(orderId == null || orderId.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/home");

            return;
        }

        // SEND TO JSP

        request.setAttribute(
                "orderId",
                orderId);

        request.getRequestDispatcher(
                "/WEB-INF/views/order-confirmation.jsp")
                .forward(request, response);
    }
}
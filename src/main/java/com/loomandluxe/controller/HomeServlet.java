package com.loomandluxe.controller;

import java.io.IOException;
import java.util.List;

import com.loomandluxe.dao.ProductDAO;
import com.loomandluxe.dao.impl.ProductDAOImpl;
import com.loomandluxe.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {

        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // FETCH PRODUCTS
        List<Product> productList =
                productDAO.getAllProducts();

        // SEND TO JSP
        request.setAttribute("products", productList);

        // FORWARD TO HOME PAGE
        request.getRequestDispatcher(
                "/WEB-INF/views/home.jsp")
                .forward(request, response);
    }
}
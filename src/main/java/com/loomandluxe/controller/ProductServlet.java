package com.loomandluxe.controller;

import java.io.IOException;
import java.util.List;

import com.loomandluxe.dao.CategoryDAO;
import com.loomandluxe.dao.ProductDAO;
import com.loomandluxe.dao.impl.CategoryDAOImpl;
import com.loomandluxe.dao.impl.ProductDAOImpl;
import com.loomandluxe.model.Category;
import com.loomandluxe.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() throws ServletException {

        productDAO = new ProductDAOImpl();
        categoryDAO = new CategoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products = null;

        // GET CATEGORY ID
        String categoryIdParam =
                request.getParameter("categoryId");

        // CHECK FILTER
        if(categoryIdParam != null &&
           !categoryIdParam.isEmpty()) {

            int categoryId =
                    Integer.parseInt(categoryIdParam);

            products =
                    productDAO.getProductsByCategory(categoryId);

        } else {

            products =
                    productDAO.getAllProducts();
        }

        // FETCH CATEGORIES
        List<Category> categories =
                categoryDAO.getAllCategories();

        // SEND TO JSP
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);

        // FORWARD
        request.getRequestDispatcher(
                "/WEB-INF/views/products.jsp")
                .forward(request, response);
    }
}
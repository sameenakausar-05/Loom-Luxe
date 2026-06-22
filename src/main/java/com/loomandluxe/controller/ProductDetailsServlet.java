package com.loomandluxe.controller;

import java.io.IOException;
import java.util.List;

import com.loomandluxe.dao.ProductDAO;
import com.loomandluxe.dao.ProductVariantDAO;
import com.loomandluxe.dao.impl.ProductDAOImpl;
import com.loomandluxe.dao.impl.ProductVariantDAOImpl;
import com.loomandluxe.model.Product;
import com.loomandluxe.model.ProductVariant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product-details")
public class ProductDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;
    private ProductVariantDAO variantDAO;

    @Override
    public void init() throws ServletException {

        productDAO = new ProductDAOImpl();
        variantDAO = new ProductVariantDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // GET PRODUCT ID FROM URL
        String productIdParam =
                request.getParameter("id");

        // VALIDATION
        if(productIdParam == null ||
           productIdParam.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/products");

            return;
        }

        int productId =
                Integer.parseInt(productIdParam);

        // FETCH PRODUCT
        Product product =
                productDAO.getProductById(productId);

        // PRODUCT NOT FOUND
        if(product == null) {

            response.sendRedirect(
                    request.getContextPath() + "/products");

            return;
        }

        // FETCH PRODUCT VARIANTS
        List<ProductVariant> variants =
                variantDAO.getVariantsByProduct(productId);

        // SEND DATA TO JSP
        request.setAttribute("product", product);
        request.setAttribute("variants", variants);

        // FORWARD TO JSP
        request.getRequestDispatcher(
                "/WEB-INF/views/product-details.jsp")
                .forward(request, response);
    }
}
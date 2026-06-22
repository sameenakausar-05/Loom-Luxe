package com.loomandluxe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.loomandluxe.dao.CartItemDAO;
import com.loomandluxe.dao.ProductDAO;
import com.loomandluxe.dao.impl.CartItemDAOImpl;
import com.loomandluxe.dao.impl.ProductDAOImpl;
import com.loomandluxe.model.CartItem;
import com.loomandluxe.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;

    private CartItemDAO cartItemDAO;

    @Override
    public void init() throws ServletException {

        productDAO = new ProductDAOImpl();

        cartItemDAO = new CartItemDAOImpl();
    }

    // =========================
    // HANDLE GET REQUESTS
    // =========================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if(action == null) {

            action = "view";
        }

        switch(action) {

            case "remove":

                removeFromCart(request, response);

                break;

            default:

                viewCart(request, response);

                break;
        }
    }

    // =========================
    // HANDLE POST REQUESTS
    // =========================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if(action == null) {

            action = "add";
        }

        switch(action) {

            case "add":

                addToCart(request, response);

                break;

            case "update":

                updateCart(request, response);

                break;

            default:

                response.sendRedirect("cart");

                break;
        }
    }

    // =========================
    // ADD TO CART
    // =========================

    private void addToCart(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int productId =
                Integer.parseInt(
                        request.getParameter("productId"));

        String size =
                request.getParameter("size");

        int quantity =
                Integer.parseInt(
                        request.getParameter("quantity"));

        Product product =
                productDAO.getProductById(productId);

        if(product == null) {

            response.sendRedirect("products");

            return;
        }

        // CREATE CART ITEM

        CartItem item = new CartItem();

        item.setProductId(product.getProductId());

        item.setProductName(product.getProductName());

        item.setPrice(product.getPrice().doubleValue());

        item.setQuantity(quantity);

        item.setImageUrl(product.getImageUrl());

        item.setSize(size);

        // SESSION

        HttpSession session = request.getSession();

        List<CartItem> cartItems =
                (List<CartItem>) session.getAttribute("cart");

        if(cartItems == null) {

            cartItems = new ArrayList<>();
        }

        // ADD ITEM USING DAO

        cartItemDAO.addToCart(cartItems, item);

        // SAVE BACK TO SESSION

        session.setAttribute("cart", cartItems);

        response.sendRedirect("cart");
    }

    // =========================
    // VIEW CART
    // =========================

    private void viewCart(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<CartItem> cartItems =
                (List<CartItem>) session.getAttribute("cart");

        if(cartItems == null) {

            cartItems = new ArrayList<>();
        }

        double total =
                cartItemDAO.getCartTotal(cartItems);

        request.setAttribute("cartItems", cartItems);

        request.setAttribute("cartTotal", total);

        request.getRequestDispatcher(
                "/WEB-INF/views/cart.jsp")
                .forward(request, response);
    }

    // =========================
    // REMOVE ITEM
    // =========================

    private void removeFromCart(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int productId =
                Integer.parseInt(
                        request.getParameter("productId"));

        String size =
                request.getParameter("size");

        HttpSession session = request.getSession();

        List<CartItem> cartItems =
                (List<CartItem>) session.getAttribute("cart");

        if(cartItems != null) {

            cartItemDAO.removeFromCart(
                    cartItems,
                    productId,
                    size
            );

            session.setAttribute("cart", cartItems);
        }

        response.sendRedirect("cart");
    }

    // =========================
    // UPDATE QUANTITY
    // =========================

    private void updateCart(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int productId =
                Integer.parseInt(
                        request.getParameter("productId"));

        String size =
                request.getParameter("size");

        int quantity =
                Integer.parseInt(
                        request.getParameter("quantity"));

        HttpSession session = request.getSession();

        List<CartItem> cartItems =
                (List<CartItem>) session.getAttribute("cart");

        if(cartItems != null) {

            cartItemDAO.updateQuantity(
                    cartItems,
                    productId,
                    size,
                    quantity
            );

            session.setAttribute("cart", cartItems);
        }

        response.sendRedirect("cart");
    }
}
package com.loomandluxe.dao.impl;

import java.util.List;

import com.loomandluxe.dao.CartItemDAO;
import com.loomandluxe.model.CartItem;

public class CartItemDAOImpl implements CartItemDAO {

    // =========================
    // ADD TO CART
    // =========================

    @Override
    public void addToCart(
            List<CartItem> cartItems,
            CartItem item) {

        // CHECK IF PRODUCT ALREADY EXISTS
        for(CartItem cartItem : cartItems) {

            if(cartItem.getProductId() == item.getProductId()
                    &&
               cartItem.getSize().equals(item.getSize())) {

                // INCREASE QUANTITY
                cartItem.setQuantity(
                        cartItem.getQuantity() + item.getQuantity());

                return;
            }
        }

        // ADD NEW ITEM
        cartItems.add(item);
    }

    // =========================
    // REMOVE FROM CART
    // =========================

    @Override
    public void removeFromCart(
            List<CartItem> cartItems,
            int productId,
            String size) {

        cartItems.removeIf(item ->

                item.getProductId() == productId
                &&
                item.getSize().equals(size)
        );
    }

    // =========================
    // UPDATE QUANTITY
    // =========================

    @Override
    public void updateQuantity(
            List<CartItem> cartItems,
            int productId,
            String size,
            int quantity) {

        for(CartItem item : cartItems) {

            if(item.getProductId() == productId
                    &&
               item.getSize().equals(size)) {

                item.setQuantity(quantity);

                return;
            }
        }
    }

    // =========================
    // GET CART TOTAL
    // =========================

    @Override
    public double getCartTotal(
            List<CartItem> cartItems) {

        double total = 0;

        for(CartItem item : cartItems) {

            total += item.getSubtotal();
        }

        return total;
    }

    // =========================
    // GET TOTAL ITEMS
    // =========================

    @Override
    public int getTotalItems(
            List<CartItem> cartItems) {

        int totalItems = 0;

        for(CartItem item : cartItems) {

            totalItems += item.getQuantity();
        }

        return totalItems;
    }
}
package com.loomandluxe.dao;

import java.util.List;

import com.loomandluxe.model.CartItem;

public interface CartItemDAO {

    // ADD ITEM
    void addToCart(
            List<CartItem> cartItems,
            CartItem item
    );

    // REMOVE ITEM
    void removeFromCart(
            List<CartItem> cartItems,
            int productId,
            String size
    );

    // UPDATE QUANTITY
    void updateQuantity(
            List<CartItem> cartItems,
            int productId,
            String size,
            int quantity
    );

    // GET TOTAL CART AMOUNT
    double getCartTotal(
            List<CartItem> cartItems
    );

    // GET TOTAL ITEMS
    int getTotalItems(
            List<CartItem> cartItems
    );
}
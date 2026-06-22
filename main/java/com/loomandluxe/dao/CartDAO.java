package com.loomandluxe.dao;

import java.util.List;

import com.loomandluxe.model.Cart;
import com.loomandluxe.model.CartItem;

public interface CartDAO {

    boolean createCart(int userId);

    Cart getCartById(int cartId);

    Cart getCartByUserId(int userId);

    Cart getOrCreateCartByUserId(int userId);

    boolean deleteCart(int cartId);

    boolean cartExistsByUserId(int userId);

    boolean addToCart(int cartId, int variantId, int quantity);

    boolean updateCartItemQuantity(int cartId, int variantId, int quantity);

    boolean removeCartItem(int cartId, int variantId);

    CartItem getCartItem(int cartId, int variantId);

    List<CartItem> getCartItems(int cartId);

    int getCartItemsCount(int cartId);

    boolean clearCart(int cartId);

    boolean isProductExistsInCart(int cartId, int variantId);
}
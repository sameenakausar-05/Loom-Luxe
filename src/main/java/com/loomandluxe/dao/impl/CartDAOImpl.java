package com.loomandluxe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.loomandluxe.dao.CartDAO;
import com.loomandluxe.model.Cart;
import com.loomandluxe.model.CartItem;
import com.loomandluxe.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    @Override
    public boolean createCart(int userId) {

        String query = "INSERT INTO cart (user_id) VALUES (?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Cart getCartById(int cartId) {

        String query = "SELECT * FROM cart WHERE cart_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return mapResultSetToCart(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Cart getCartByUserId(int userId) {

        String query = "SELECT * FROM cart WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return mapResultSetToCart(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Cart getOrCreateCartByUserId(int userId) {

        Cart cart = getCartByUserId(userId);

        if (cart != null) {
            return cart;
        }

        boolean created = createCart(userId);

        if (created) {
            return getCartByUserId(userId);
        }

        return null;
    }

    @Override
    public boolean deleteCart(int cartId) {

        String query = "DELETE FROM cart WHERE cart_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean cartExistsByUserId(int userId) {

        String query = "SELECT COUNT(*) FROM cart WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean addToCart(int cartId, int variantId, int quantity) {

        String query = "INSERT INTO cart_items "
                + "(cart_id, variant_id, quantity) "
                + "VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, variantId);
            preparedStatement.setInt(3, quantity);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateCartItemQuantity(int cartId,
                                          int variantId,
                                          int quantity) {

        String query = "UPDATE cart_items "
                + "SET quantity = ? "
                + "WHERE cart_id = ? AND variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, cartId);
            preparedStatement.setInt(3, variantId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeCartItem(int cartId, int variantId) {

        String query = "DELETE FROM cart_items "
                + "WHERE cart_id = ? AND variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, variantId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public CartItem getCartItem(int cartId, int variantId) {

        String query = "SELECT * FROM cart_items "
                + "WHERE cart_id = ? AND variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, variantId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                CartItem cartItem = new CartItem();

                cartItem.setCartItemId(resultSet.getInt("cart_item_id"));
                cartItem.setCartId(resultSet.getInt("cart_id"));
                cartItem.setVariantId(resultSet.getInt("variant_id"));
                cartItem.setQuantity(resultSet.getInt("quantity"));
                cartItem.setAddedAt(resultSet.getTimestamp("added_at"));

                return cartItem;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public java.util.List<CartItem> getCartItems(int cartId) {

        java.util.List<CartItem> cartItems = new java.util.ArrayList<>();

        String query = "SELECT * FROM cart_items WHERE cart_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                CartItem cartItem = new CartItem();

                cartItem.setCartItemId(resultSet.getInt("cart_item_id"));
                cartItem.setCartId(resultSet.getInt("cart_id"));
                cartItem.setVariantId(resultSet.getInt("variant_id"));
                cartItem.setQuantity(resultSet.getInt("quantity"));
                cartItem.setAddedAt(resultSet.getTimestamp("added_at"));

                cartItems.add(cartItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    @Override
    public int getCartItemsCount(int cartId) {

        String query = "SELECT SUM(quantity) FROM cart_items WHERE cart_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public boolean clearCart(int cartId) {

        String query = "DELETE FROM cart_items WHERE cart_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean isProductExistsInCart(int cartId, int variantId) {

        String query = "SELECT COUNT(*) FROM cart_items "
                + "WHERE cart_id = ? AND variant_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, variantId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Cart mapResultSetToCart(ResultSet resultSet)
            throws SQLException {

        Cart cart = new Cart();

        cart.setCartId(resultSet.getInt("cart_id"));
        cart.setUserId(resultSet.getInt("user_id"));
        cart.setCreatedAt(resultSet.getTimestamp("created_at"));

        return cart;
    }
}
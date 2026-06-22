package com.loomandluxe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.loomandluxe.dao.OrderDAO;
import com.loomandluxe.model.Order;
import com.loomandluxe.model.OrderItem;
import com.loomandluxe.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public int createOrder(Order order) {

        String query = "INSERT INTO orders "
                + "(user_id, total_amount, order_status, payment_method, "
                + "delivery_name, delivery_phone, address_line1, "
                + "address_line2, city, state, delivery_country, pincode) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             query,
                             PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setBigDecimal(2, order.getTotalAmount());
            preparedStatement.setString(3, order.getOrderStatus());
            preparedStatement.setString(4, order.getPaymentMethod());
            preparedStatement.setString(5, order.getDeliveryName());
            preparedStatement.setString(6, order.getDeliveryPhone());
            preparedStatement.setString(7, order.getAddressLine1());
            preparedStatement.setString(8, order.getAddressLine2());
            preparedStatement.setString(9, order.getCity());
            preparedStatement.setString(10, order.getState());
            preparedStatement.setString(11, order.getDeliveryCountry());
            preparedStatement.setString(12, order.getPincode());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                ResultSet generatedKeys =
                        preparedStatement.getGeneratedKeys();

                if (generatedKeys.next()) {

                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public boolean addOrderItem(OrderItem orderItem) {

        String query = "INSERT INTO order_items "
                + "(order_id, variant_id, quantity, price) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getVariantId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setBigDecimal(4, orderItem.getPrice());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String orderStatus) {

        String query = "UPDATE orders "
                + "SET order_status = ? "
                + "WHERE order_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setString(1, orderStatus);
            preparedStatement.setInt(2, orderId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) {

        String query = "DELETE FROM orders WHERE order_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Order getOrderById(int orderId) {

        String query = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return mapResultSetToOrder(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM orders "
                + "WHERE user_id = ? "
                + "ORDER BY created_at DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                orders.add(mapResultSetToOrder(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM orders ORDER BY created_at DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                orders.add(mapResultSetToOrder(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {

        List<OrderItem> orderItems = new ArrayList<>();

        String query = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                OrderItem orderItem = new OrderItem();

                orderItem.setOrderItemId(
                        resultSet.getInt("order_item_id"));

                orderItem.setOrderId(
                        resultSet.getInt("order_id"));

                orderItem.setVariantId(
                        resultSet.getInt("variant_id"));

                orderItem.setQuantity(
                        resultSet.getInt("quantity"));

                orderItem.setPrice(
                        resultSet.getBigDecimal("price"));

                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {

        String query = "SELECT * FROM order_items "
                + "WHERE order_item_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderItemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                OrderItem orderItem = new OrderItem();

                orderItem.setOrderItemId(
                        resultSet.getInt("order_item_id"));

                orderItem.setOrderId(
                        resultSet.getInt("order_id"));

                orderItem.setVariantId(
                        resultSet.getInt("variant_id"));

                orderItem.setQuantity(
                        resultSet.getInt("quantity"));

                orderItem.setPrice(
                        resultSet.getBigDecimal("price"));

                return orderItem;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getTotalOrdersByUserId(int userId) {

        String query = "SELECT COUNT(*) FROM orders WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

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
    public boolean isOrderExists(int orderId) {

        String query = "SELECT COUNT(*) FROM orders WHERE order_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Order mapResultSetToOrder(ResultSet resultSet)
            throws SQLException {

        Order order = new Order();

        order.setOrderId(resultSet.getInt("order_id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setTotalAmount(resultSet.getBigDecimal("total_amount"));
        order.setOrderStatus(resultSet.getString("order_status"));
        order.setPaymentMethod(resultSet.getString("payment_method"));
        order.setDeliveryName(resultSet.getString("delivery_name"));
        order.setDeliveryPhone(resultSet.getString("delivery_phone"));
        order.setAddressLine1(resultSet.getString("address_line1"));
        order.setAddressLine2(resultSet.getString("address_line2"));
        order.setCity(resultSet.getString("city"));
        order.setState(resultSet.getString("state"));
        order.setDeliveryCountry(resultSet.getString("delivery_country"));
        order.setPincode(resultSet.getString("pincode"));
        order.setCreatedAt(resultSet.getTimestamp("created_at"));

        return order;
    }
}
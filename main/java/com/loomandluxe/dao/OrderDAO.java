package com.loomandluxe.dao;

import java.util.List;

import com.loomandluxe.model.Order;
import com.loomandluxe.model.OrderItem;

public interface OrderDAO {

    int createOrder(Order order);

    boolean addOrderItem(OrderItem orderItem);

    boolean updateOrderStatus(int orderId, String orderStatus);

    boolean deleteOrder(int orderId);

    Order getOrderById(int orderId);

    List<Order> getOrdersByUserId(int userId);

    List<Order> getAllOrders();

    List<OrderItem> getOrderItemsByOrderId(int orderId);

    OrderItem getOrderItemById(int orderItemId);

    int getTotalOrdersByUserId(int userId);

    boolean isOrderExists(int orderId);
}
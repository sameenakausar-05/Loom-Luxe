package com.loomandluxe.dao;

import java.util.List;

import com.loomandluxe.model.OrderItem;

public interface OrderItemDAO {

    boolean addOrderItem(OrderItem orderItem);

    boolean updateOrderItem(OrderItem orderItem);

    boolean deleteOrderItem(int orderItemId);

    boolean deleteOrderItemsByOrderId(int orderId);

    OrderItem getOrderItemById(int orderItemId);

    List<OrderItem> getOrderItemsByOrderId(int orderId);

    List<OrderItem> getAllOrderItems();

    boolean isOrderItemExists(int orderItemId);

    int getTotalQuantityByOrderId(int orderId);
}
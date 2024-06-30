package com.logate.summer.dto.order.query;

import com.logate.summer.dto.order.command.OrderItemDTO;
import com.logate.summer.entities.OrderStatus;

import java.util.List;

public class OrderQueryDTOWithID {

    Long orderId;
    Long customerId;
    List<OrderItemDTO> orderItems;
    OrderStatus status;

    public OrderQueryDTOWithID(Long orderId, Long customerId, List<OrderItemDTO> orderItems, OrderStatus status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

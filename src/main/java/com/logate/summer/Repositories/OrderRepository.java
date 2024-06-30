package com.logate.summer.Repositories;

import com.logate.summer.dto.order.command.OrderDTO;
import com.logate.summer.dto.order.command.OrderItemDTO;
import com.logate.summer.dto.order.query.OrderQueryDTO;
import com.logate.summer.dto.order.query.OrderQueryDTOWithID;
import com.logate.summer.entities.Order;
import com.logate.summer.entities.OrderItem;
import com.logate.summer.entities.OrderStatus;
import com.logate.summer.mapper.OrderMapper;
import com.logate.summer.mapper.OrderItemMapper;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    private final Map<Long, Order> orders = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<OrderQueryDTO> getAll() {
        return orders.values().stream()
                .map(OrderMapper.INSTANCE::orderToOrderQueryDTO)
                .collect(Collectors.toList());
    }

    public OrderQueryDTO getById(Long id) {
        Order order = orders.get(id);
        if (order != null) {
            return OrderMapper.INSTANCE.orderToOrderQueryDTO(order);
        }
        return null;
    }

    public List<OrderQueryDTO> getByCustomerId(Long customerId) {
        return orders.values().stream()
                .filter(order -> Objects.equals(order.getCustomerId(), customerId))
                .map(OrderMapper.INSTANCE::orderToOrderQueryDTO)
                .collect(Collectors.toList());
    }

    public OrderQueryDTOWithID create(OrderDTO orderDTO) {
        Long orderId = nextId.getAndIncrement();
        Order order = OrderMapper.INSTANCE.orderDTOToOrder(orderDTO);
        order.setId(orderId);
        orders.put(orderId, order);
        return OrderMapper.INSTANCE.orderToOrderQueryDTOWithID(order);
    }

    public OrderQueryDTOWithID update(Long orderId, OrderStatus status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(status);
            orders.put(orderId, order);
            return OrderMapper.INSTANCE.orderToOrderQueryDTOWithID(order);
        }
        return null;
    }

    public void delete(Long orderId) {
        orders.remove(orderId);
    }

    public void addOrderItem(Long orderId, OrderItemDTO orderItemDTO) {
        Order order = orders.get(orderId);
        if (order != null) {
            OrderItem orderItem = OrderItemMapper.INSTANCE.orderItemDTOToOrderItem(orderItemDTO);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
    }

    public void updateOrderItem(Long orderId, OrderItemDTO orderItemDTO) {
        Order order = orders.get(orderId);
        if (order != null) {
            for (OrderItem orderItem : order.getOrderItems()) {
                if (Objects.equals(orderItem.getId(), orderItemDTO.getId())) {
                    orderItem.setProductId(orderItemDTO.getProductId());
                    orderItem.setQuantity(orderItemDTO.getQuantity());
                }
            }
        }
    }

    public void removeOrderItem(Long orderId, Long orderItemId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.getOrderItems().removeIf(item -> Objects.equals(item.getId(), orderItemId));
        }
    }
}

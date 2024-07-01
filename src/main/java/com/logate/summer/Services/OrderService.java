package com.logate.summer.Services;

import com.logate.summer.dto.order.command.OrderDTO;
import com.logate.summer.dto.order.command.OrderItemDTO;
import com.logate.summer.dto.order.query.OrderQueryDTO;
import com.logate.summer.dto.order.query.OrderQueryDTOWithID;
import com.logate.summer.entities.Order;
import com.logate.summer.entities.OrderItem;
import com.logate.summer.entities.OrderStatus;
import com.logate.summer.mapper.OrderMapper;
import com.logate.summer.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Cacheable(value = "orders", key = "#id")
    public OrderQueryDTOWithID getById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            return OrderMapper.INSTANCE.orderToOrderQueryDTOWithID(order);
        }
        return null;
    }

    @Cacheable(value = "orders", key = "'all'")
    public List<OrderQueryDTO> getAll() {
        List<Order> orders = orderRepository.findAll();
        return OrderMapper.INSTANCE.ordersToOrderQueryDTOs(orders);
    }

    @Cacheable(value = "orders", key = "'customer_' + #customerId")
    public List<OrderQueryDTO> getByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return OrderMapper.INSTANCE.ordersToOrderQueryDTOs(orders);
    }

    @CachePut(value = "orders", key = "#result.orderId")
    public OrderQueryDTOWithID create(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.orderDTOToOrder(orderDTO);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        calculateTotalPrice(order);
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.INSTANCE.orderToOrderQueryDTOWithID(savedOrder);
    }

    @CachePut(value = "orders", key = "#orderId")
    public OrderQueryDTOWithID update(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            Order updatedOrder = orderRepository.save(order);
            return OrderMapper.INSTANCE.orderToOrderQueryDTOWithID(updatedOrder);
        }
        return null;
    }

    @CacheEvict(value = "orders", key = "#orderId")
    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public void addOrderItem(Long orderId, OrderItemDTO orderItemDTO) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            OrderItem orderItem = OrderMapper.INSTANCE.orderItemDTOToOrderItem(orderItemDTO);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
            calculateTotalPrice(order);
            orderRepository.save(order);
        }
    }

    public void updateOrderItem(Long orderId, OrderItemDTO orderItemDTO) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            for (OrderItem orderItem : order.getOrderItems()) {
                if (Objects.equals(orderItem.getId(), orderItemDTO.getId())) {
                    orderItem.setProductId(orderItemDTO.getProductId());
                    orderItem.setQuantity(orderItemDTO.getQuantity());
                    break;
                }
            }
            calculateTotalPrice(order);
            orderRepository.save(order);
        }
    }

    public void removeOrderItem(Long orderId, Long orderItemId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.getOrderItems().removeIf(item -> Objects.equals(item.getId(), orderItemId));
            calculateTotalPrice(order);
            orderRepository.save(order);
        }
    }

    private void calculateTotalPrice(Order order) {
        BigDecimal totalPrice = order.getOrderItems().stream()
                .map(item -> item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);
    }
}

package com.logate.summer.Services;

import com.logate.summer.Repositories.OrderRepository;
import com.logate.summer.dto.order.command.OrderDTO;
import com.logate.summer.dto.order.command.OrderItemDTO;
import com.logate.summer.dto.order.query.OrderQueryDTO;
import com.logate.summer.dto.order.query.OrderQueryDTOWithID;
import com.logate.summer.entities.OrderStatus;
import com.logate.summer.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Cacheable(value = "orders", key = "#id")
    public OrderQueryDTO getById(Long id) {
        return orderRepository.getById(id);
    }

    @Cacheable(value = "orders", key = "'all'")
    public List<OrderQueryDTO> getAll() {
        return orderRepository.getAll();
    }

    @Cacheable(value = "orders", key = "'customer_' + #customerId")
    public List<OrderQueryDTO> getByCustomerId(Long customerId) {
        return orderRepository.getByCustomerId(customerId);
    }

    @CachePut(value = "orders", key = "#result.id")
    public OrderQueryDTOWithID create(OrderDTO orderDTO) {
        return orderRepository.create(orderDTO);
    }

    @CachePut(value = "orders", key = "#orderId")
    public OrderQueryDTOWithID update(Long orderId, OrderStatus status) {
        return orderRepository.update(orderId, status);
    }

    @CacheEvict(value = "orders", key = "#orderId")
    public void delete(Long orderId) {
        orderRepository.delete(orderId);
    }

    public void addOrderItem(Long orderId, OrderItemDTO orderItemDTO) {
        orderRepository.addOrderItem(orderId, orderItemDTO);
    }

    public void updateOrderItem(Long orderId, OrderItemDTO orderItemDTO) {
        orderRepository.updateOrderItem(orderId, orderItemDTO);
    }

    public void removeOrderItem(Long orderId, Long orderItemId) {
        orderRepository.removeOrderItem(orderId, orderItemId);
    }
}

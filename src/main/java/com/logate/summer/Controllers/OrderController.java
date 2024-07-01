package com.logate.summer.Controllers;

import com.logate.summer.dto.order.command.OrderDTO;
import com.logate.summer.dto.order.command.OrderItemDTO;
import com.logate.summer.dto.order.query.OrderQueryDTO;
import com.logate.summer.dto.order.query.OrderQueryDTOWithID;
import com.logate.summer.entities.OrderStatus;
import com.logate.summer.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderQueryDTOWithID getOrderById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<OrderQueryDTO> getOrdersByCustomerId(@PathVariable Long customerId) {
        return orderService.getByCustomerId(customerId);
    }

    @GetMapping
    public List<OrderQueryDTO> getAllOrders() {
        return orderService.getAll();
    }

    @PostMapping
    public OrderQueryDTOWithID createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.create(orderDTO);
    }

    @PutMapping("/{orderId}")
    public OrderQueryDTOWithID updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {
        return orderService.update(orderId, status);
    }

    @PostMapping("/{orderId}/items")
    public void addOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDTO orderItemDTO) {
        orderService.addOrderItem(orderId, orderItemDTO);
    }

    @PutMapping("/{orderId}/items")
    public void updateOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDTO orderItemDTO) {
        orderService.updateOrderItem(orderId, orderItemDTO);
    }

    @DeleteMapping("/{orderId}/items/{orderItemId}")
    public void removeOrderItem(@PathVariable Long orderId, @PathVariable Long orderItemId) {
        orderService.removeOrderItem(orderId, orderItemId);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.delete(orderId);
    }
}

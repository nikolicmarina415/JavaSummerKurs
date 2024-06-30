package com.logate.summer.Controllers;

import com.logate.summer.Services.OrderService;
import com.logate.summer.dto.order.command.OrderDTO;
import com.logate.summer.dto.order.command.OrderItemDTO;
import com.logate.summer.dto.order.query.OrderQueryDTO;
import com.logate.summer.dto.order.query.OrderQueryDTOWithID;
import com.logate.summer.entities.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderQueryDTO> getOrderById(@PathVariable Long id) {
        OrderQueryDTO order = orderService.getById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderQueryDTO>> getAllOrders() {
        List<OrderQueryDTO> orders = orderService.getAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderQueryDTO>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<OrderQueryDTO> orders = orderService.getByCustomerId(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderQueryDTOWithID> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderQueryDTOWithID createdOrder = orderService.create(orderDTO);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderQueryDTOWithID> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatus status) {
        OrderQueryDTOWithID updatedOrder = orderService.update(orderId, status);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<Void> addOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDTO orderItemDTO) {
        orderService.addOrderItem(orderId, orderItemDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}/items")
    public ResponseEntity<Void> updateOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDTO orderItemDTO) {
        orderService.updateOrderItem(orderId, orderItemDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<Void> removeOrderItem(@PathVariable Long orderId, @PathVariable Long itemId) {
        orderService.removeOrderItem(orderId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

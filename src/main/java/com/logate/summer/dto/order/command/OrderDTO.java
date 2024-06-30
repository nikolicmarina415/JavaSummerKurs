package com.logate.summer.dto.order.command;

import com.logate.summer.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private Long customerId;
    private LocalDateTime createdAt;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private List<OrderItemDTO> orderItems;



    public OrderDTO(Long id, Long customerId, LocalDateTime createdAt, BigDecimal totalPrice, OrderStatus status, List<OrderItemDTO> orderItems) {
        this.id = id;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderItems = orderItems;
    }
}

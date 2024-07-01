package com.logate.summer.dto.order.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    Long customerId;
    LocalDateTime createdAt;
    BigDecimal totalPrice;
    List<OrderItemDTO> orderItems;
}

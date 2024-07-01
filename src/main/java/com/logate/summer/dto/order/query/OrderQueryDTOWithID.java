package com.logate.summer.dto.order.query;

import com.logate.summer.dto.order.command.OrderItemDTO;
import com.logate.summer.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryDTOWithID {

    private Long orderId;
    private Long customerId;
    private List<OrderItemDTO> orderItems;
    private OrderStatus status;
}

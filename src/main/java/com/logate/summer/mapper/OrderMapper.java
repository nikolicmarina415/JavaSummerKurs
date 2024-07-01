package com.logate.summer.mapper;

import com.logate.summer.dto.order.command.OrderDTO;
import com.logate.summer.dto.order.command.OrderItemDTO;
import com.logate.summer.dto.order.query.OrderQueryDTO;
import com.logate.summer.dto.order.query.OrderQueryDTOWithID;
import com.logate.summer.entities.Order;
import com.logate.summer.entities.OrderItem;
import com.logate.summer.entities.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderDTOToOrder(OrderDTO orderDTO);

    OrderDTO orderToOrderDTO(Order order);

    List<OrderQueryDTO> ordersToOrderQueryDTOs(List<Order> orders);

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "orderItems", source = "order.orderItems")
    OrderQueryDTOWithID orderToOrderQueryDTOWithID(Order order);

    List<OrderQueryDTOWithID> ordersToOrderQueryDTOWithIDs(List<Order> orders);

    OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO);

    OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);
}

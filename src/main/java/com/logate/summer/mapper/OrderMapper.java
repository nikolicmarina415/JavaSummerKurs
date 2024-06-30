package com.logate.summer.mapper;

import com.logate.summer.dto.order.command.OrderDTO;
import com.logate.summer.dto.order.query.OrderQueryDTO;
import com.logate.summer.dto.order.query.OrderQueryDTOWithID;
import com.logate.summer.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO orderToOrderDTO(Order order);

    Order orderDTOToOrder(OrderDTO orderDTO);

    OrderQueryDTO orderToOrderQueryDTO(Order order);

    OrderQueryDTOWithID orderToOrderQueryDTOWithID(Order order);

    List<OrderQueryDTO> ordersToOrderQueryDTOs(List<Order> orders);
}

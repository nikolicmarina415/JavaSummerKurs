package com.logate.summer.mapper;

import com.logate.summer.dto.order.command.OrderItemDTO;
import com.logate.summer.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);

    OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO);

    List<OrderItemDTO> orderItemsToOrderItemDTOs(List<OrderItem> orderItems);

    List<OrderItem> orderItemDTOsToOrderItems(List<OrderItemDTO> orderItemDTOs);
}

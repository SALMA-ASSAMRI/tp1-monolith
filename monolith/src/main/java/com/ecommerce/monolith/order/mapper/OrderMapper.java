package com.ecommerce.monolith.order.mapper;
import com.ecommerce.monolith.order.dto.OrderDTO;
import com.ecommerce.monolith.order.entity.Order;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    List<OrderDTO> toDTOList(List<Order> orders);
}

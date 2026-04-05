package com.ecommerce.monolith.order.service;
import com.ecommerce.monolith.customer.service.CustomerService;
import com.ecommerce.monolith.order.dto.CreateOrderRequest;
import com.ecommerce.monolith.order.dto.OrderDTO;
import com.ecommerce.monolith.order.entity.Order;
import com.ecommerce.monolith.order.mapper.OrderMapper;
import com.ecommerce.monolith.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CustomerService customerService;

    @Override
    public OrderDTO createOrder(CreateOrderRequest request) {
        if (!customerService.customerExists(request.getCustomerId()))
            throw new RuntimeException("Customer not found: " + request.getCustomerId());
        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .orderDate(LocalDateTime.now())
                .build();
        return mapper.toDTO(repository.save(order));
    }
    @Override
    public List<OrderDTO> getOrdersByCustomer(Long customerId) {
        return mapper.toDTOList(repository.findByCustomerId(customerId));
    }
    @Override
    public List<OrderDTO> getAllOrders() {
        return mapper.toDTOList(repository.findAll());
    }
}

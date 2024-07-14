package com.whipcodey.OrderService.service;

import com.whipcodey.OrderService.dto.OrderLineItemsDTO;
import com.whipcodey.OrderService.dto.OrderRequest;
import com.whipcodey.OrderService.model.Order;
import com.whipcodey.OrderService.model.OrderLineItems;
import com.whipcodey.OrderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        order.setOrderLineItemsList(orderRequest.getOrderLineItemsDTOList()
                .stream()
                .map(this::mapToDTO)
                .toList());

        orderRepository.save(order);
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        return OrderLineItems.builder()
                .id(orderLineItemsDTO.getId())
                .skuCode(orderLineItemsDTO.getSkuCode())
                .quantity(orderLineItemsDTO.getQuantity())
                .price(orderLineItemsDTO.getPrice())
                .build();
    }
}

package com.example.bookstorewebapp.service.order.impl;

import com.example.bookstorewebapp.dto.order.CreateOrderRequestDto;
import com.example.bookstorewebapp.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.bookstorewebapp.repository.order.OrderRepository;
import com.example.bookstorewebapp.service.order.OrderService;
import com.example.bookstorewebapp.service.shoppingcart.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;

    @Override
    public String placeOrder(Long userId, CreateOrderRequestDto requestDto) {
        ShoppingCartResponseDto byUserId = shoppingCartService.getByUserId(userId);
        return "place order " + userId + requestDto.getShippingAddress();
    }

    @Override
    public String getOrderedItems(Long userId) {
        return " get order items 1, 2, 3 " + userId;
    }

    @Override
    public String getSpecificOrderedItem(Long userId) {
        return "get_spec_item:{ \"value\":\"1\"} " + userId;
    }

    @Override
    public String getAllOrders(Long userId) {
        return "{all:orders}" + userId;
    }

    @Override
    public String updateStatus(Long userId) {
        return "status updated " + userId;
    }
}

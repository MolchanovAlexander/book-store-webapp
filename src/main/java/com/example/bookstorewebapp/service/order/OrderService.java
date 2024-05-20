package com.example.bookstorewebapp.service.order;

import com.example.bookstorewebapp.dto.order.CreateOrderRequestDto;
import com.example.bookstorewebapp.dto.order.OrderResponseDto;

public interface OrderService {
    OrderResponseDto placeOrder(Long userId, CreateOrderRequestDto requestDto);

    String getOrderedItems(Long userId);

    String getSpecificOrderedItem(Long userId);

    String getAllOrders(Long userId);

    String updateStatus(Long userId);
}

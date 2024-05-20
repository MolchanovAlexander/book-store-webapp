package com.example.bookstorewebapp.service.order;

import com.example.bookstorewebapp.dto.order.CreateOrderRequestDto;

public interface OrderService {
    String placeOrder(Long userId, CreateOrderRequestDto requestDto);

    String getOrderedItems(Long userId);

    String getSpecificOrderedItem(Long userId);

    String getAllOrders(Long userId);

    String updateStatus(Long userId);
}

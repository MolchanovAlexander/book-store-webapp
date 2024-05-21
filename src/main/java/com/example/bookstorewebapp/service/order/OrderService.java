package com.example.bookstorewebapp.service.order;

import com.example.bookstorewebapp.dto.order.CreateOrderRequestDto;
import com.example.bookstorewebapp.dto.order.OrderResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto placeOrder(Long userId, CreateOrderRequestDto requestDto);

    String getOrderedItems(Long userId);

    String getSpecificOrderedItem(Long userId);

    List<OrderResponseDto> getAllOrders(Long userId, Pageable pageable);

    String updateStatus(Long userId);
}

package com.example.bookstorewebapp.service.order.impl;

import com.example.bookstorewebapp.dto.order.CreateOrderRequestDto;
import com.example.bookstorewebapp.dto.order.OrderResponseDto;
import com.example.bookstorewebapp.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.bookstorewebapp.exception.EntityNotFoundException;
import com.example.bookstorewebapp.mapper.OrderMapper;
import com.example.bookstorewebapp.model.Order;
import com.example.bookstorewebapp.model.Status;
import com.example.bookstorewebapp.model.User;
import com.example.bookstorewebapp.repository.order.OrderRepository;
import com.example.bookstorewebapp.repository.user.UserRepository;
import com.example.bookstorewebapp.service.order.OrderService;
import com.example.bookstorewebapp.service.shoppingcart.ShoppingCartService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    @Override
    public OrderResponseDto placeOrder(Long userId, CreateOrderRequestDto requestDto) {
        ShoppingCartResponseDto byUserId = shoppingCartService.getByUserId(userId);
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new EntityNotFoundException("There is no user with id:" + userId)
        );
        Order model = orderMapper.toModel(requestDto, byUserId);
        model.setOrderDate(LocalDateTime.now());
        model.setStatus(Status.PENDING);
        model.setUser(user);
        model.setTotal(new BigDecimal(789));
        model.setOrderItems(Set.of());
        System.out.println(model);
        orderRepository.save(model);
        return orderMapper.toDto(model);
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

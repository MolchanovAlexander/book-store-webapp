package com.example.bookstorewebapp.service.order.impl;

import com.example.bookstorewebapp.dto.order.CreateOrderRequestDto;
import com.example.bookstorewebapp.dto.order.OrderResponseDto;
import com.example.bookstorewebapp.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.bookstorewebapp.exception.EntityNotFoundException;
import com.example.bookstorewebapp.mapper.OrderItemMapper;
import com.example.bookstorewebapp.mapper.OrderMapper;
import com.example.bookstorewebapp.model.Book;
import com.example.bookstorewebapp.model.CartItem;
import com.example.bookstorewebapp.model.Order;
import com.example.bookstorewebapp.model.OrderItem;
import com.example.bookstorewebapp.model.Status;
import com.example.bookstorewebapp.model.User;
import com.example.bookstorewebapp.repository.order.OrderRepository;
import com.example.bookstorewebapp.repository.orderitem.OrderItemRepository;
import com.example.bookstorewebapp.repository.user.UserRepository;
import com.example.bookstorewebapp.service.book.BookService;
import com.example.bookstorewebapp.service.cartitem.CartItemService;
import com.example.bookstorewebapp.service.order.OrderService;
import com.example.bookstorewebapp.service.shoppingcart.ShoppingCartService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final CartItemService cartItemService;
    private final OrderItemMapper orderItemMapper;
    private final BookService bookService;
    private final OrderItemRepository orderItemRepository;


    @Override
    public OrderResponseDto placeOrder(Long userId, CreateOrderRequestDto requestDto) {
        ShoppingCartResponseDto shoppingCart = shoppingCartService.getByUserId(userId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("There is no user with id:" + userId)
        );
        Order model = orderMapper.toModel(requestDto, shoppingCart);
        Set<CartItem> cartItems = cartItemService.findAllById(userId);//System.out.println(oi);
        List<BigDecimal> priceList = cartItems.stream()
                .map(c -> c.getBook().getPrice()
                        .multiply(BigDecimal.valueOf(c.getQuantity())))
                .toList();
        BigDecimal total = new BigDecimal(0);
        for (BigDecimal bigDecimal : priceList) {
            total = total.add(bigDecimal);
        }
        model.setOrderDate(LocalDateTime.now());
        model.setStatus(Status.PENDING);
        model.setUser(user);
        model.setTotal(total);

        Order savedOrder = orderRepository.save(model);

        Set<OrderItem> orderItems = cartItems.stream()
                .map(c -> orderItemMapper.toModel(c, model))
                .collect(Collectors.toSet());

        List<OrderItem> oi = orderItemRepository.saveAll(orderItems);
        savedOrder.setOrderItems(new HashSet<>(oi));
        return orderMapper.toDto(savedOrder);
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
    public List<OrderResponseDto> getAllOrders(Long userId, Pageable pageable) {
        return orderRepository.findAll(pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public String updateStatus(Long userId) {
        return "status updated " + userId;
    }
}

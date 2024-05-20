package com.example.bookstorewebapp.controller;

import com.example.bookstorewebapp.dto.order.CreateOrderRequestDto;
import com.example.bookstorewebapp.dto.order.OrderResponseDto;
import com.example.bookstorewebapp.model.User;
import com.example.bookstorewebapp.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Orders management",
        description = "Orders for managing ShoppingCart"
)
@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @Operation(
            summary = "Place an order",
            description = "Place an order from shopping cart"
    )
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderResponseDto placeOrder(
            Authentication authentication,
            @Valid @RequestBody CreateOrderRequestDto requestDto
            ) {
        User user = (User) authentication.getPrincipal();
        return orderService.placeOrder(user.getId(), requestDto);
    }

    @Operation(
            summary = "Get ordered items",
            description = "get ordered items"
    )
    @GetMapping("/{orderId}/items")
    public String getOrderedItems(
            Authentication authentication,
            @PathVariable Long orderId,
            Pageable pageable
    ) {
        User user = (User) authentication.getPrincipal();
        return orderService.getOrderedItems(user.getId());
    }

    @Operation(
            summary = "Get specific ordered item",
            description = "get specific ordered item entity "
    )
    @GetMapping("/{orderId}/items/{id}")
    public String getSpecificOrderedItem(
            @PathVariable Long orderId,
            @PathVariable Long id,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return orderService.getSpecificOrderedItem(user.getId());
    }

    @Operation(
            summary = "Get user's order",
            description = "get  user's order "
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String getOrders(
            Authentication authentication,
            Pageable pageable
    ) {
        User user = (User) authentication.getPrincipal();
        return orderService.getAllOrders(user.getId());
    }

    @Operation(
            summary = "Update status user order",
            description = "update status of the order "
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    public String changeStatusOfOrder(
            Authentication authentication,
            Pageable pageable,
            @PathVariable Long id) {
        User user = (User) authentication.getPrincipal();
        return orderService.updateStatus(id);
    }
}

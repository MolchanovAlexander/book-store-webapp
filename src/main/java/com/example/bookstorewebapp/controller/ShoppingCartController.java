package com.example.bookstorewebapp.controller;

import com.example.bookstorewebapp.dto.cartitem.CreateCartItemRequestDto;
import com.example.bookstorewebapp.model.User;
import com.example.bookstorewebapp.service.shoppingcart.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "ShoppingCart management",
        description = "Endpoints for managing ShoppingCart"
)
@RestController
@RequestMapping(value = "/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(
            summary = "Create ShoppingCart",
            description = "create ShoppingCart entity after first query"
    )
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addItemToShoppingCart(
            @Valid @RequestBody CreateCartItemRequestDto requestDto,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        System.out.println(user.getId() + " " + user.getEmail());
        shoppingCartService.save(user.getId(), requestDto);
    }


}

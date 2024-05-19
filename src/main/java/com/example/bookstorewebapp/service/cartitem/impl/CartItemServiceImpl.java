package com.example.bookstorewebapp.service.cartitem.impl;

import com.example.bookstorewebapp.dto.cartitem.CartItemResponseDto;
import com.example.bookstorewebapp.dto.cartitem.CreateCartItemRequestDto;
import com.example.bookstorewebapp.mapper.CategoryMapper;
import com.example.bookstorewebapp.model.CartItem;
import com.example.bookstorewebapp.repository.cartitem.CartItemRepository;
import com.example.bookstorewebapp.repository.shoppingcart.ShoppingCartRepository;
import com.example.bookstorewebapp.service.cartitem.CartItemService;
import com.example.bookstorewebapp.service.shoppingcart.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
    private static final String MESSAGE_CATEGORY_NOT_EXIST = "category";
    private final CartItemRepository cartItemRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CartItemResponseDto getById(Long id) {
        return null;
    }

    @Override
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }
}

package com.example.bookstorewebapp.service.shoppingcart;

import com.example.bookstorewebapp.dto.cartitem.CartItemResponseDto;
import com.example.bookstorewebapp.dto.cartitem.CreateCartItemRequestDto;

public interface ShoppingCartService {
   // List<CategoryResponseDto> findAll(Pageable pageable);
    void deleteById(Long id);
    CartItemResponseDto getById(Long id);

    void save(Long id, CreateCartItemRequestDto requestDto);
}

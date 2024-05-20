package com.example.bookstorewebapp.service.cartitem;

import com.example.bookstorewebapp.model.CartItem;
import java.util.Set;

public interface CartItemService {
    Set<CartItem> findAllById(Long id);

    void save(CartItem cartItem);

    void deleteByUserId(Long itemId);

    void updateById(Long id, Integer quantity);
}
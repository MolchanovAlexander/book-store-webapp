package com.example.bookstorewebapp.service.cartitem.impl;

import com.example.bookstorewebapp.exception.EntityNotFoundException;
import com.example.bookstorewebapp.model.CartItem;
import com.example.bookstorewebapp.repository.cartitem.CartItemRepository;
import com.example.bookstorewebapp.service.cartitem.CartItemService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    @Override
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public void updateById(Long id, Integer quantity) {
        isEntityExist(id);
        cartItemRepository.updateById(id, quantity);
    }

    public void isEntityExist(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new EntityNotFoundException("There is no cart item with id: " + id);
        }
    }

    @Override
    public Set<CartItem> findAllById(Long id) {
        return cartItemRepository.findAllByShoppingCartId(id);
    }

    @Override
    public void deleteById(Long itemId) {
        isEntityExist(itemId);
        cartItemRepository.deleteCartItemById(itemId);
    }
}

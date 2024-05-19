package com.example.bookstorewebapp.service.shoppingcart.impl;

import com.example.bookstorewebapp.dto.cartitem.CartItemResponseDto;
import com.example.bookstorewebapp.dto.cartitem.CreateCartItemRequestDto;
import com.example.bookstorewebapp.mapper.CartItemMapper;
import com.example.bookstorewebapp.mapper.ShoppingCartMapper;
import com.example.bookstorewebapp.model.CartItem;
import com.example.bookstorewebapp.model.ShoppingCart;
import com.example.bookstorewebapp.repository.shoppingcart.ShoppingCartRepository;
import com.example.bookstorewebapp.service.cartitem.CartItemService;
import com.example.bookstorewebapp.service.shoppingcart.ShoppingCartService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final String MESSAGE_CATEGORY_NOT_EXIST = "category";
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemService cartItemService;
    private final ShoppingCartMapper cartMapper;
    private final CartItemMapper cartItemMapper;


    @Override
    public void deleteById(Long id) {

    }

    @Override
    public CartItemResponseDto getById(Long id) {
        return null;
    }

    @Override
    public void save(Long id, CreateCartItemRequestDto requestDto) {
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(id);
        cartItem.setShoppingCart(shoppingCart);
        shoppingCart.setCartItems(Set.of(cartItem));
        cartItemService.save(cartItem);

    }
}

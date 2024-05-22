package com.example.bookstorewebapp.service.shoppingcart.impl;

import com.example.bookstorewebapp.dto.cartitem.CreateCartItemRequestDto;
import com.example.bookstorewebapp.dto.cartitem.UpdateCartItemRequestDto;
import com.example.bookstorewebapp.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.bookstorewebapp.exception.EntityNotFoundException;
import com.example.bookstorewebapp.mapper.CartItemMapper;
import com.example.bookstorewebapp.mapper.ShoppingCartMapper;
import com.example.bookstorewebapp.model.CartItem;
import com.example.bookstorewebapp.model.ShoppingCart;
import com.example.bookstorewebapp.model.User;
import com.example.bookstorewebapp.repository.shoppingcart.ShoppingCartRepository;
import com.example.bookstorewebapp.service.book.BookService;
import com.example.bookstorewebapp.service.cartitem.CartItemService;
import com.example.bookstorewebapp.service.shoppingcart.ShoppingCartService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper cartMapper;
    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;
    private final BookService bookService;

    @Override
    public ShoppingCart createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto getByUserId(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository
                .findShoppingCartByUserId(id).orElseThrow(
                        () -> new EntityNotFoundException("There is no cart with id: " + id)
                );
        return cartMapper.toDto(shoppingCart);
    }

    @Override
    public void update(Long id, UpdateCartItemRequestDto requestDto) {
        cartItemService.updateById(id, requestDto.getQuantity());
    }

    @Override
    public void save(User user, CreateCartItemRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(user.getId())
                .orElse(createShoppingCart(user));
        bookService.isExist(requestDto.getBookId());
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        cartItem.setShoppingCart(shoppingCart);
        saveCartItem(requestDto, shoppingCart, cartItem);
    }

    private void saveCartItem(
            CreateCartItemRequestDto requestDto, ShoppingCart shoppingCart, CartItem cartItem
    ) {
        List<CartItem> cartItems = cartItemService
                .findAllByShoppingCartId(shoppingCart.getId()).stream()
                .filter(c -> Objects.equals(c.getBook().getId(), requestDto.getBookId()))
                .toList();
        if (cartItems.isEmpty()) {
            cartItemService.save(cartItem);
        } else {
            cartItemService.updateById(
                    cartItems.get(0).getId(),
                    requestDto.getQuantity() + cartItems.get(0).getQuantity()
            );
        }
    }

    @Override
    public void deleteCartItemById(Long itemId) {
        cartItemService.deleteById(itemId);
    }

    private ShoppingCart checkShoppingCart(Long id) {
        return shoppingCartRepository
                .findShoppingCartByUserId(id).orElseThrow(
                        () -> new EntityNotFoundException(
                                "There is no cart for user with id: " + id)
                );
    }
}

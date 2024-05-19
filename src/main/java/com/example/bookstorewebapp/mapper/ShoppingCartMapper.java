package com.example.bookstorewebapp.mapper;

import com.example.bookstorewebapp.config.MapperConfig;
import com.example.bookstorewebapp.dto.cartitem.CartItemResponseDto;
import com.example.bookstorewebapp.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.bookstorewebapp.model.ShoppingCart;
import com.example.bookstorewebapp.model.User;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {CartItemMapper.class})
public interface ShoppingCartMapper {

//    private Long id;
//    private Long userId;
//    private Set<CartItemResponseDto> cartItems;
    @Mapping(target = "userId", source = "user", qualifiedByName = "userIdFromUser")
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);

    @AfterMapping
    default void setCartItems(
            ShoppingCart shoppingCart,
            @MappingTarget ShoppingCartResponseDto responseDto,
            CartItemMapper cartItemMapper
    ) {
        Set<CartItemResponseDto> cartItems = shoppingCart.getCartItems().stream()
                .map(cartItemMapper::toDto).collect(Collectors.toSet());
        responseDto.setCartItems(cartItems);
    }

    @Named(value = "userIdFromUser")
    default Long bookIdByBook(User user) {
        return user.getId();
    }
}

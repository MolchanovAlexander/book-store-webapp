package com.example.bookstorewebapp.mapper;

import com.example.bookstorewebapp.config.MapperConfig;
import com.example.bookstorewebapp.dto.book.BookDto;
import com.example.bookstorewebapp.dto.order.CreateOrderRequestDto;
import com.example.bookstorewebapp.dto.order.OrderResponseDto;
import com.example.bookstorewebapp.dto.shoppingcart.ShoppingCartResponseDto;
import com.example.bookstorewebapp.model.Book;
import com.example.bookstorewebapp.model.Category;
import com.example.bookstorewebapp.model.Order;
import com.example.bookstorewebapp.model.OrderItem;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {CartItemMapper.class})
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    //@Mapping(source = "user.id", target = "total")
    OrderResponseDto toDto(Order order);
//    Set<Long> map(Set<OrderItem> value);
//    Long map(OrderItem value);
    @Mapping(target = "orderDate", ignore = true)
    Order toModel(CreateOrderRequestDto requestDto, ShoppingCartResponseDto responseDto);

    @BeforeMapping
    default void setOrderItems(@MappingTarget OrderResponseDto responseDto, Order order) {
//        List<Long> categoryIds = order.getOrderItems().stream()
//                .map(OrderItem::getId)
//                .toList();
        responseDto.setOrderItems(Set.of(1L, 2L, 3L));
    }

    @Named(value = "total")
    default Long bookIdByBook(Book book) {
        return book.getId();
    }
}

package com.example.bookstorewebapp.service.cartitem;

import com.example.bookstorewebapp.dto.cartitem.CartItemResponseDto;
import com.example.bookstorewebapp.dto.cartitem.CreateCartItemRequestDto;
import com.example.bookstorewebapp.dto.category.CategoryResponseDto;
import com.example.bookstorewebapp.dto.category.CreateCategoryRequestDto;
import com.example.bookstorewebapp.model.CartItem;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;

public interface CartItemService {
   // List<CategoryResponseDto> findAll(Pageable pageable);

    CartItemResponseDto getById(Long id);

    void save(CartItem cartItem);
}

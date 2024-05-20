package com.example.bookstorewebapp.repository.shoppingcart;

import com.example.bookstorewebapp.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("SELECT s FROM ShoppingCart s WHERE s.user.id = :id")
    ShoppingCart findShoppingCartByUserId(Long id);
}

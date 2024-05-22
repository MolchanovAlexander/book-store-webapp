package com.example.bookstorewebapp.repository.shoppingcart;

import com.example.bookstorewebapp.model.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("SELECT s FROM ShoppingCart s LEFT JOIN FETCH s.cartItems WHERE s.user.id = :id")
    Optional<ShoppingCart> findShoppingCartByUserId(Long id);
}

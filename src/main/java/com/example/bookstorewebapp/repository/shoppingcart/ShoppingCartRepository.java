package com.example.bookstorewebapp.repository.shoppingcart;

import com.example.bookstorewebapp.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>,
        JpaSpecificationExecutor<ShoppingCart> {
    ShoppingCart findShoppingCartByUserId(Long id);
}

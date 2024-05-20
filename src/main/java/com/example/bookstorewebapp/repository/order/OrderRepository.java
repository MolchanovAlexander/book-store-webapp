package com.example.bookstorewebapp.repository.order;

import com.example.bookstorewebapp.model.Order;
import com.example.bookstorewebapp.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user.id = :id")
    ShoppingCart findOrderByUserId(Long id);
}

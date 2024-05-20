package com.example.bookstorewebapp.repository.cartitem;

import com.example.bookstorewebapp.model.CartItem;
import jakarta.transaction.Transactional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long>,
        JpaSpecificationExecutor<CartItem> {
    Set<CartItem> findAllByShoppingCartId(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.id = :id")
    void deleteCartItemById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.id = :id")
    void updateById(@Param("id") Long id, @Param("quantity") Integer quantity);
}

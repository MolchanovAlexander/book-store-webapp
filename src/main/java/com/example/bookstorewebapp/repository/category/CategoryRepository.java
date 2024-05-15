package com.example.bookstorewebapp.repository.category;

import com.example.bookstorewebapp.model.Category;
import com.example.bookstorewebapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Long>,
        JpaSpecificationExecutor<Category> {
   // Role findByRole(Role.RoleName roleName);
}

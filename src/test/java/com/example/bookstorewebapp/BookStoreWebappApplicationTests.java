package com.example.bookstorewebapp;

import com.example.bookstorewebapp.config.CustomMySqlContainer;
import com.example.bookstorewebapp.model.Book;
import com.example.bookstorewebapp.model.Category;
import com.example.bookstorewebapp.repository.book.BookRepository;
import com.example.bookstorewebapp.repository.category.CategoryRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest
class BookStoreWebappApplicationTests {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @BeforeAll
    static void beforeAll() {
        CustomMySqlContainer.getInstance().start();
    }

    @DynamicPropertySource
    static void registerMySqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CustomMySqlContainer.getInstance()::getJdbcUrl);
        registry.add("spring.datasource.username", CustomMySqlContainer.getInstance()::getUsername);
        registry.add("spring.datasource.password", CustomMySqlContainer.getInstance()::getPassword);
    }

    @Test
    void contextLoads() {
        // Your test code here
        Category category = new Category();
        category.setId(1L);
        category.setName("test category");
        categoryRepository.save(category);
        Book book = new Book();
        book.setIsbn("ISBN 978-1-721-11223-7");
        book.setAuthor("test Author");
        book.setPrice(new BigDecimal("11.11"));
        book.setCategories(Set.of(category));
        bookRepository.save(book);

        List<Book> actual = bookRepository.findAllByCategoryId(1L, Pageable.unpaged());
        Assertions.assertEquals(1, actual.size());
    }

}

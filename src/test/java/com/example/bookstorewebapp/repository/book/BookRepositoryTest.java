package com.example.bookstorewebapp.repository.book;

import com.example.bookstorewebapp.model.Book;
import com.example.bookstorewebapp.model.Category;
import com.example.bookstorewebapp.repository.category.CategoryRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    private static final String TITLE = "Test Book Title";
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private Book testBook;

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
        Category category = new Category();
        category.setName("test category");
        categoryRepository.save(category);
        bookRepository.deleteAll();
        testBook = new Book();
        testBook.setIsbn("ISBN 978-1-721-11223-7");
        testBook.setAuthor("test Author");
        testBook.setPrice(new BigDecimal("11.11"));
        testBook.setCategories(Set.of(category));
        testBook.setTitle(TITLE);
        bookRepository.save(testBook);
        System.out.println(bookRepository.findAll());
    }

    @Test
    @DisplayName("Find all books by category id")
    void findAllByCategoryId_Id1_ReturnsNotEmptyList() {
        List<Book> actual = bookRepository.findAllByCategoryId(1L, Pageable.unpaged());
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(testBook.getId(), actual.get(0).getId());
    }

    @Test
    void findAll() {
        List<Book> books = bookRepository.findAll(Pageable.unpaged()).getContent();
        Assertions.assertFalse(books.isEmpty());
        Assertions.assertEquals(1, books.size());
        Assertions.assertEquals(testBook.getId(), books.get(0).getId());
    }

    @Test
    void findById() {
        Book foundBook = bookRepository.findById(testBook.getId()).orElse(null);
        Assertions.assertNotNull(foundBook);
        Assertions.assertEquals(testBook.getId(), foundBook.getId());
    }
}
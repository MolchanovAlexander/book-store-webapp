package com.example.bookstorewebapp.repository.book;


import com.example.bookstorewebapp.model.Book;
import com.example.bookstorewebapp.model.Category;
import com.example.bookstorewebapp.repository.category.CategoryRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    @DisplayName("""
            Find all books by category id
            """)
    void findAllByCategoryId_Id1_ReturnsNotEmptyList() {
        Category category = new Category();
        category.setId(1L);
        category.setName("test category");
        categoryRepository.save(category);
        Book book = new Book();
        book.setIsbn("ISBN 978-1-721-11223-7");
        book.setAuthor("test Author");
        book.setPrice(new BigDecimal("11.11"));
        book.setCategories(Set.of(category));
        book.setTitle("Set.of(category)");
        bookRepository.save(book);

        List<Book> actual = bookRepository.findAllByCategoryId(1L, Pageable.unpaged());
        Assertions.assertEquals(1, actual.size());
    }

    @Test
    void findAll() {
    }

    @Test
    void testFindAll() {
    }

    @Test
    void findById() {
    }
}
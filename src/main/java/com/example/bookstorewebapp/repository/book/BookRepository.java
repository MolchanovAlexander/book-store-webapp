package com.example.bookstorewebapp.repository.book;

import com.example.bookstorewebapp.model.Book;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>,
        JpaSpecificationExecutor<Book> {
    @Query("FROM Book b JOIN FETCH b.categories c WHERE c.id = :categoryId")
    List<Book> findAllByCategoryId(Long categoryId, Pageable pageable);

}

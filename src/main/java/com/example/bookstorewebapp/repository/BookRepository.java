package com.example.bookstorewebapp.repository;

import com.example.bookstorewebapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}

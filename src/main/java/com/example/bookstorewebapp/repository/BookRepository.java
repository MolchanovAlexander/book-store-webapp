package com.example.bookstorewebapp.repository;

import com.example.bookstorewebapp.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}

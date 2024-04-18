package com.example.bookstorewebapp.service;

import com.example.bookstorewebapp.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}

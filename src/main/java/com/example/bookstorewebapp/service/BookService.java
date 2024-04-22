package com.example.bookstorewebapp.service;

import com.example.bookstorewebapp.dto.BookDto;
import com.example.bookstorewebapp.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto create(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);
}

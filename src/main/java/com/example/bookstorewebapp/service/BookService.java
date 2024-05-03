package com.example.bookstorewebapp.service;

import com.example.bookstorewebapp.dto.BookDto;
import com.example.bookstorewebapp.dto.BookSearchParameters;
import com.example.bookstorewebapp.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto create(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    List<BookDto> search(BookSearchParameters params, Pageable pageable);
}

package com.example.bookstorewebapp.service.book;

import com.example.bookstorewebapp.dto.book.BookDto;
import com.example.bookstorewebapp.dto.book.BookSearchParameters;
import com.example.bookstorewebapp.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto create(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    List<BookDto> search(BookSearchParameters params, Pageable pageable);
}

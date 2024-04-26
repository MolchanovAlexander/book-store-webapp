package com.example.bookstorewebapp.service.impl;

import com.example.bookstorewebapp.dto.BookDto;
import com.example.bookstorewebapp.dto.CreateBookRequestDto;
import com.example.bookstorewebapp.exception.EntityNotFoundException;
import com.example.bookstorewebapp.mapper.BookMapper;
import com.example.bookstorewebapp.model.Book;
import com.example.bookstorewebapp.repository.BookRepository;
import com.example.bookstorewebapp.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto create(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("There is no book to delete with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public BookDto updateById(Long id, CreateBookRequestDto requestDto) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("There is no book to update with id: " + id);
        }
        Book book = bookMapper.toModel(requestDto);
        book.setId(id);
        return bookMapper.toDto(bookRepository.save(book));
    }
}

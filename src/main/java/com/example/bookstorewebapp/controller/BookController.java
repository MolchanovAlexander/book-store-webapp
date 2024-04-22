package com.example.bookstorewebapp.controller;

import com.example.bookstorewebapp.dto.BookDto;
import com.example.bookstorewebapp.dto.CreateBookRequestDto;
import com.example.bookstorewebapp.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class BookController {
    private static final String URL =
            "https://peppy-horse-b3cd93.netlify.app/";
    private static final String LOCAL_URL = "http://localhost:3000";
    private final BookService bookService;

    @GetMapping
    @CrossOrigin(origins = {LOCAL_URL, URL})
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @PostMapping
    @CrossOrigin(origins = {LOCAL_URL, URL})
    public BookDto createBook(@RequestBody CreateBookRequestDto requestDto) {
        return bookService.create(requestDto);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = {LOCAL_URL, URL})
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }
}

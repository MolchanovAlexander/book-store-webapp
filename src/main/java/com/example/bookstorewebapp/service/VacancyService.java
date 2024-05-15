package com.example.bookstorewebapp.service;

import com.example.bookstorewebapp.dto.book.BookDto;
import com.example.bookstorewebapp.service.book.BookService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class VacancyService {
    private final Map<Long, BookDto> vacancies = new HashMap<>();
    private final BookService bookService;

    @PostConstruct
    public void init() {
        List<BookDto> list = bookService.findAll();
        for (BookDto vacancy : list) {
            vacancies.put(vacancy.getId(), vacancy);
        }

    }

    public List<BookDto> getJuniorVacancies() {
        return vacancies.values().stream().toList();
    }

//    public VacancyDto get(String id) {
//        return vacancies.get(id);
//    }
//
//    public List<VacancyDto> getMiddleVacanciesMenu() {
//        return vacancies.values().stream()
//                .filter(v -> v.getTitle()
//                        .toLowerCase().contains("middle")).toList();
//    }
//
//    public List<VacancyDto> getSeniorVacanciesMenu() {
//        return vacancies.values().stream()
//                .filter(v -> v.getTitle()
//                        .toLowerCase().contains("senior")).toList();
//    }
}
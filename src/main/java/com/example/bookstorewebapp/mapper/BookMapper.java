package com.example.bookstorewebapp.mapper;

import com.example.bookstorewebapp.config.MapperConfig;
import com.example.bookstorewebapp.dto.BookDto;
import com.example.bookstorewebapp.dto.CreateBookRequestDto;
import com.example.bookstorewebapp.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}

package com.example.bookstorewebapp;

import com.example.bookstorewebapp.dto.CreateBookRequestDto;
import com.example.bookstorewebapp.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreWebappApplication {
    @Autowired
    private BookService bookService;
    private final String url = "https://res.cloudinary.com/dvlngfltj/"
            + "image/upload/v1695894739/samples/animals/cat.jpg";

    public static void main(String[] args) {
        SpringApplication.run(BookStoreWebappApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            CreateBookRequestDto iphone = new CreateBookRequestDto();
            iphone.setPrice(BigDecimal.valueOf(674));
            iphone.setTitle("Dniwe 1220");
            iphone.setDescription("Very useless book");
            iphone.setIsbn("dSgCbvJ8x/dQ");
            iphone.setAuthor("Clown Dinar");
            iphone.setCoverImage(url);
            CreateBookRequestDto iphone2 = new CreateBookRequestDto();
            iphone2.setPrice(BigDecimal.valueOf(674));
            iphone2.setTitle("222 1220");
            iphone2.setDescription("Very useless book");
            iphone2.setIsbn("2dSgCbvJ8x22");
            iphone2.setAuthor("TWO BOOK");
            iphone2.setCoverImage(url);
            bookService.create(iphone);
            bookService.create(iphone2);
            System.out.println(bookService.findAll());
        };
    }
}

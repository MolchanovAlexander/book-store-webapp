package com.example.bookstorewebapp;

import com.example.bookstorewebapp.model.Book;
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
    private String url = "https://res.cloudinary.com/dvlngfltj/"
            + "image/upload/v1695894739/samples/animals/cat.jpg";

    public static void main(String[] args) {
        SpringApplication.run(BookStoreWebappApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book iphone = new Book();
            iphone.setPrice(BigDecimal.valueOf(674));
            iphone.setTitle("Dniwe 1220");
            iphone.setDescription("Very useless book");
            iphone.setIsbn("dSgCbvJ8x/dQ");
            iphone.setAuthor("Clown Dinar");
            iphone.setCoverImage(url);
            bookService.save(iphone);
            System.out.println(bookService.findAll());
        };
    }
}

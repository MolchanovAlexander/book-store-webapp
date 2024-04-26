package com.example.bookstorewebapp.repository.book;

import com.example.bookstorewebapp.dto.BookSearchParameters;
import com.example.bookstorewebapp.model.Book;
import com.example.bookstorewebapp.repository.SpecificationBuilder;
import com.example.bookstorewebapp.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    @Autowired
    private SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> defaultSpec = Specification.where(null);
        if (searchParameters.titles() != null && searchParameters.titles().length > 0) {
            defaultSpec = defaultSpec
                    .and(bookSpecificationProviderManager.getSpecificationProvider("title")
                            .getSpecification(searchParameters.titles()));
        }
        if (searchParameters.authors() != null && searchParameters.authors().length > 0) {
            defaultSpec = defaultSpec
                    .and(bookSpecificationProviderManager.getSpecificationProvider("author")
                            .getSpecification(searchParameters.authors()));
        }
        return defaultSpec;
    }
}

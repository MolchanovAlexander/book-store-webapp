package com.example.bookstorewebapp.service.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.bookstorewebapp.dto.book.BookDto;
import com.example.bookstorewebapp.dto.book.BookSearchParameters;
import com.example.bookstorewebapp.dto.book.CreateBookRequestDto;
import com.example.bookstorewebapp.exception.EntityNotFoundException;
import com.example.bookstorewebapp.mapper.BookMapper;
import com.example.bookstorewebapp.model.Book;
import com.example.bookstorewebapp.repository.book.BookRepository;
import com.example.bookstorewebapp.repository.book.BookSpecificationBuilder;
import com.example.bookstorewebapp.service.book.impl.BookServiceImpl;
import com.example.bookstorewebapp.service.category.CategoryService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookSpecificationBuilder specificationBuilder;

    @InjectMocks
    private BookServiceImpl bookService;
    private final Long bookId = 1L;

    @Test
    @DisplayName("Create Book successfully")
    void createBook_Success() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setCategoryIds(Set.of(1L, 2L));
        Book book = new Book();
        BookDto responseDto = new BookDto();

        doNothing().when(categoryService).isAllCategoriesPresent(requestDto.getCategoryIds());
        when(bookMapper.toModel(requestDto)).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(responseDto);

        BookDto actual = bookService.create(requestDto);

        verify(bookRepository, times(1)).save(any(Book.class));
        assertEquals(actual, responseDto);
    }

    @Test
    @DisplayName("Update Book by ID throws EntityNotFoundException when book does not exist")
    void updateBookById_ThrowsEntityNotFoundException_WhenBookNotExist() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setCategoryIds(Set.of(1L, 2L));

        when(bookRepository.existsById(bookId)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            bookService.updateById(bookId, requestDto);
        });
        assertEquals("There is no book with id: " + bookId, exception.getMessage());
    }

    @Test
    @DisplayName("Find Book by ID returns book")
    void findBookById_ReturnsBook() {
        Book book = new Book();
        book.setId(bookId);
        BookDto responseDto = new BookDto();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(responseDto);

        BookDto actual = bookService.findById(bookId);

        verify(bookRepository, times(1)).findById(bookId);
        assertEquals(actual, responseDto);
    }

    @Test
    @DisplayName("Find Book by ID throws EntityNotFoundException when book does not exist")
    void findBookById_ThrowsEntityNotFoundException_WhenBookNotExist() {
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            bookService.findById(bookId);
        });
    }

    @Test
    @DisplayName("Delete Book by ID successfully")
    void deleteBookById_Success() {
        when(bookRepository.existsById(bookId)).thenReturn(true);

        bookService.deleteById(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    @DisplayName("Delete Book by ID throws EntityNotFoundException when book does not exist")
    void deleteBookById_ThrowsEntityNotFoundException_WhenBookNotExist() {
        when(bookRepository.existsById(bookId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            bookService.deleteById(bookId);
        });
    }

    @Test
    @DisplayName("Find all Books returns books")
    void findAllBooks_ReturnsBooks() {
        Pageable pageable = Pageable.unpaged();
        Book book = new Book();
        BookDto responseDto = new BookDto();

        when(bookRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(book)));
        when(bookMapper.toDto(book)).thenReturn(responseDto);

        List<BookDto> actual = bookService.findAll(pageable);

        verify(bookRepository, times(1)).findAll(pageable);
        assertEquals(List.of(responseDto), actual);
    }

    @Test
    @DisplayName("Search Books returns books")
    void searchBooks_ReturnsBooks() {
        Pageable pageable = Pageable.unpaged();
        BookSearchParameters params = new BookSearchParameters(
                new String[]{"Title1"}, new String[]{"Author1"});
        Book book = new Book();
        BookDto responseDto = new BookDto();

        when(specificationBuilder.build(params)).thenReturn((Specification<Book>)
                (root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
        when(bookRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(book)));
        when(bookMapper.toDto(book)).thenReturn(responseDto);

        List<BookDto> actual = bookService.search(params, pageable);

        verify(bookRepository, times(1))
                .findAll(any(Specification.class), any(Pageable.class));
        assertEquals(List.of(responseDto), actual);
    }
}

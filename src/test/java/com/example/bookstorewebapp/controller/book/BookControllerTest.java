package com.example.bookstorewebapp.controller.book;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.bookstorewebapp.dto.book.BookDto;
import com.example.bookstorewebapp.dto.book.BookSearchParameters;
import com.example.bookstorewebapp.dto.book.CreateBookRequestDto;
import com.example.bookstorewebapp.service.book.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName(" Create valid book")
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    void createBook_ValidRequest_ReturnsCreatedBook() throws Exception {
        CreateBookRequestDto requestDto = createRequestDto();
        System.out.println(requestDto);
        BookDto responseDto = createResponseDto();
        given(bookService.create(any(CreateBookRequestDto.class)))
                .willReturn(responseDto);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void getAllBooks_ValidRequest_ReturnsListOfBooks() throws Exception {
        List<BookDto> books = Collections.singletonList(createResponseDto());
        Pageable pageable = PageRequest.of(0, 10);
        given(bookService.findAll(pageable)).willReturn(books);

        mockMvc.perform(get("/books")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(books)));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void getBookById_ValidRequest_ReturnsBook() throws Exception {
        Long id = 1L;
        BookDto bookDto = createResponseDto();
        given(bookService.findById(id)).willReturn(bookDto);

        mockMvc.perform(get("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bookDto)));
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    void updateBook_ValidRequest_ReturnsUpdatedBook() throws Exception {
        Long id = 1L;
        CreateBookRequestDto requestDto = createRequestDto();
        System.out.println(requestDto);
        BookDto responseDto = createResponseDto();
        given(bookService.updateById(eq(id), any(CreateBookRequestDto.class)))
                .willReturn(responseDto);

        mockMvc.perform(put("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isAccepted())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    void deleteBook_ValidRequest_ReturnsNoContent() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void searchBooks_ValidRequest_ReturnsBooks() throws Exception {
        BookSearchParameters searchParameters = new BookSearchParameters(
                new String[]{"Title1"}, new String[]{"Author1"});
        List<BookDto> books = Collections.singletonList(createResponseDto());
        Pageable pageable = PageRequest.of(0, 10);

        given(bookService.search(any(BookSearchParameters.class), eq(pageable))).willReturn(books);

        mockMvc.perform(get("/books/search")
                        .param("titles", "Title1")
                        .param("authors", "Author1")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(books)));
    }

    private BookDto createResponseDto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Title1");
        bookDto.setAuthor("Author1");
        bookDto.setIsbn("ISBN 978-1-721-11223-7");
        bookDto.setPrice(BigDecimal.TEN);
        bookDto.setDescription("Description1");
        return bookDto;
    }

    private CreateBookRequestDto createRequestDto() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setIsbn("ISBN 978-1-721-11223-7");
        requestDto.setTitle("Title1");
        requestDto.setAuthor("Author1");
        requestDto.setIsbn("ISBN 978-1-721-11223-7");
        requestDto.setPrice(BigDecimal.TEN);
        requestDto.setDescription("Description1");
        requestDto.setCategoryIds(Set.of(1L));
        return requestDto;
    }
}

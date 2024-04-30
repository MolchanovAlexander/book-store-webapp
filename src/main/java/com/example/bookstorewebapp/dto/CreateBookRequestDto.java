package com.example.bookstorewebapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotBlank
    @Size(min = 2, max = 255)
    private String title;
    @NotBlank
    @Size(min = 2, max = 255)
    private String author;
    @NotBlank
    @Pattern(regexp = "^\\d{13}$", message = "The ISBN must contain 13 digits")
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}

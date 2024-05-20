package com.example.bookstorewebapp.dto.order;

import com.example.bookstorewebapp.model.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Long user_id;
    private List<String> orderItems;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private Status status;
}

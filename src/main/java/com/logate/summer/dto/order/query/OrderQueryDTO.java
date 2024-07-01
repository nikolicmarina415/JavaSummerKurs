package com.logate.summer.dto.order.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryDTO {

     private Long id;
     private Long customerId;
     private LocalDateTime createdAt;
     private BigDecimal totalPrice;
     private String status;
}

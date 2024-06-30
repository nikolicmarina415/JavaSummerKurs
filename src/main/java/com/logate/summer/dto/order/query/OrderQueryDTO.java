package com.logate.summer.dto.order.query;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderQueryDTO {

     Long id;
     Long customerId;
     LocalDateTime createdAt;
     BigDecimal totalPrice;
     String status;

}

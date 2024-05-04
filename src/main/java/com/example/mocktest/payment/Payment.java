package com.example.mocktest.payment;

import com.example.mocktest.payment.Currency;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long paymentId;

    private UUID customerId;

    private BigDecimal amount;

    private Currency currency;

    private String source;

    private String description;
}

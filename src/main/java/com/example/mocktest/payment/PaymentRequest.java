package com.example.mocktest.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class PaymentRequest {

    private Payment payment;
}

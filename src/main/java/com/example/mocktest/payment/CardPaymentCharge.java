package com.example.mocktest.payment;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CardPaymentCharge {

    private boolean isCardDebited;
}

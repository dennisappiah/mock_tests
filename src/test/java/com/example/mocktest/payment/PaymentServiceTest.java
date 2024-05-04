package com.example.mocktest.payment;

import com.example.mocktest.customers.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private PaymentService underTest;

    @Mock
    private  CustomerRepository customerRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private  CardPaymentCharger cardPaymentCharger;

    @BeforeEach
    void setUp() {
    }


}
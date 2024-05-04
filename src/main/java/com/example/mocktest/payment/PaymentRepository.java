package com.example.mocktest.payment;

import com.example.mocktest.payment.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}

package com.example.mocktest.payment;


import com.example.mocktest.customers.Customer;
import com.example.mocktest.customers.CustomerRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PaymentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

   // @Test
    public void shouldCreatePaymentSuccessfully() throws Exception{
        // Given
        UUID id = UUID.randomUUID();
        String name = "kofi";
        String phoneNumber = "+4434567890";

        Customer customer = Customer.builder()
                .id(id)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();

        CustomerRequest customerRequest = new CustomerRequest(customer);

        // ... Payment
        long paymentId = 1L;

        Payment payment = new Payment(
                paymentId,
                id,
                new BigDecimal("100.00"),
                Currency.GBP,
                "x0x0x0x0",
                "Zakat"
        );

        // ... Payment request
        PaymentRequest paymentRequest = new PaymentRequest(payment);


        // When
        ResultActions customerRegResultActions = mockMvc.perform(put("/api/v1/customer-registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(customerRequest))));


        ResultActions paymentResultActions = mockMvc.perform(post("/api/v1/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(paymentRequest))));

        // Then
        // both customer registration and payment requests are 200 status code
        customerRegResultActions.andExpect(status().isOk());
        paymentResultActions.andExpect(status().isOk());
    }



    private String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }

}

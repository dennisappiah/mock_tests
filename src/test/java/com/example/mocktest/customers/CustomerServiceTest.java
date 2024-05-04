package com.example.mocktest.customers;

import com.example.mocktest.utils.PhoneNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

class CustomerServiceTest {

    private CustomerServiceImpl underTest;

    @Mock
    private CustomerRepository customerRepository;

    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;
    @Mock
    private PhoneNumberValidator phoneNumberValidator;


    @BeforeEach
    void setUp(){
     MockitoAnnotations.initMocks(this);

        underTest = new CustomerServiceImpl(
                customerRepository, phoneNumberValidator
        );
    }

   @Test
    void shouldSaveNewCustomer(){
        // Given
        UUID id = UUID.randomUUID();
        String name = "kofi";
        String phoneNumber = "+4434567890";

        var customer = Customer.builder()
                .id(id)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();

        //...request
        CustomerRequest customerRequest = new CustomerRequest(customer);

        //...no customer with phone number passed
        given(customerRepository.selectCustomerByPhoneNumber(phoneNumber))
                .willReturn(Optional.empty());

        // When
        underTest.registerNewCustomer(customerRequest);

        // Then
        then(customerRepository).should().save(customerArgumentCaptor.capture());
        Customer savedCustomer = customerArgumentCaptor.getValue();

        assertNotNull(savedCustomer);
        assertEquals(id, savedCustomer.getId());
        assertEquals(name, savedCustomer.getName());
        assertEquals(phoneNumber, savedCustomer.getPhoneNumber());
    }

    //@Test
    void shouldNotSaveNewCustomer(){
        // Given
        UUID id = UUID.randomUUID();
        String name = "kofi";
        String phoneNumber = "+4434567890";

        var customer = Customer.builder()
                .id(id)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();

        //...request
        CustomerRequest customerRequest = new CustomerRequest(customer);

        //...customer with phone number passed
        given(customerRepository.selectCustomerByPhoneNumber(phoneNumber))
                .willReturn(Optional.of(customer));

        // When
        underTest.registerNewCustomer(customerRequest);

        //Then
        then(customerRepository).should(never()).save(any(Customer.class));

    }

    void shouldThrowWhenPhoneNumberIsTaken(){

    }

}
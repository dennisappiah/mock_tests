package com.example.mocktest.customers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

// Testing JPA queries
@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    @Test
    public void shouldSelectCustomerByPhone(){
        // Given
        UUID id = UUID.randomUUID();
        String name = "kofi";
        String phoneNumber = "0000";

        var customer = Customer.builder()
                .id(id)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();

        //When
        underTest.save(customer);

        Optional<Customer> optionalCustomer =
                underTest.selectCustomerByPhoneNumber(phoneNumber);

        //Then
        assertTrue(optionalCustomer.isPresent());

        assertEquals("kofi", optionalCustomer.get().getName());
        assertEquals("0000", optionalCustomer.get().getPhoneNumber());
    }

    @Test
    public void shouldSaveCustomer(){
        // given
        UUID id = UUID.randomUUID();
        var customer = Customer.builder()
                .id(id)
                .name("kofi")
                .phoneNumber("0000")
                .build();

        // when
        underTest.save(customer);

        // Then
        Optional<Customer> optionalCustomer = underTest.findById(id);

        assertTrue(optionalCustomer.isPresent());

        assertEquals("kofi", optionalCustomer.get().getName());
        assertEquals("0000", optionalCustomer.get().getPhoneNumber());

    }


    void shouldNotSaveCustomerWhenNameIsNull(){
        // given
        UUID id = UUID.randomUUID();
        var customer = Customer.builder()
                .id(id)
                .name("kofi")
                .phoneNumber("0000")
                .build();
//DataIntegrityViolationException.class
        // when
        underTest.save(customer);


    }
}
package com.example.mocktest.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CustomerRequest {

    private Customer customer;

}

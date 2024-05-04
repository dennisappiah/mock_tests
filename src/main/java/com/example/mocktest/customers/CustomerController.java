package com.example.mocktest.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/customer-registration")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PutMapping
    public void registerNewCustomer(
            @RequestBody CustomerRequest request) {
        customerService.registerNewCustomer(request);
    }

}

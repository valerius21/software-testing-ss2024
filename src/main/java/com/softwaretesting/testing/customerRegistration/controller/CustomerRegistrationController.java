package com.softwaretesting.testing.customerRegistration.controller;

import com.softwaretesting.testing.dto.inbound.CustomerInDTO;
import com.softwaretesting.testing.dto.outbound.CustomerOutDTO;
import com.softwaretesting.testing.model.Customer;
import com.softwaretesting.testing.customerRegistration.service.CustomerRegistrationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@RestController
@RequestMapping("api/v1/customer-registration")
@Validated
public class CustomerRegistrationController {

    private final CustomerRegistrationService customerRegistrationService;

    public CustomerRegistrationController(CustomerRegistrationService customerRegistrationService) {
        this.customerRegistrationService = customerRegistrationService;
    }


    @PostMapping
    public CustomerOutDTO registerNewCustomer(@Valid @RequestBody CustomerInDTO dto) {
        Customer customer = dto.toEntity();
        customerRegistrationService.registerNewCustomer(customer);
        return new CustomerOutDTO(customer);
    }
}

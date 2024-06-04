package com.softwaretesting.testing.customerRegistration.service;

import com.softwaretesting.testing.exception.BadRequestException;
import com.softwaretesting.testing.dao.CustomerRepository;
import com.softwaretesting.testing.model.Customer;
import com.softwaretesting.testing.validator.PhoneNumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerRegistrationService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerRegistrationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Transactional(rollbackFor = Exception.class)
    public Customer registerNewCustomer(Customer customer) {
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        boolean isValid = phoneNumberValidator.validate(customer.getPhoneNumber());

        if (!isValid) {
            throw new BadRequestException("Invalid Phone Number");
        }

        Optional<Customer> existsPhoneNumber = customerRepository.selectCustomerByPhoneNumber(customer.getPhoneNumber());

        if (existsPhoneNumber.isPresent()) {
            Customer existingCustomer = existsPhoneNumber.get();
            if (existingCustomer.getName().equals(customer.getName())) {
                throw new IllegalStateException("You are already registered");
            }
            throw new BadRequestException(
                    "Phone Number " + customer.getPhoneNumber() + " taken");
        }

        return customerRepository.save(customer);
    }
}







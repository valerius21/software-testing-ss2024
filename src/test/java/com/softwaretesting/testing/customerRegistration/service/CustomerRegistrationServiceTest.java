package com.softwaretesting.testing.customerRegistration.service;

import com.softwaretesting.testing.dao.CustomerRepository;
import com.softwaretesting.testing.exception.BadRequestException;
import com.softwaretesting.testing.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class CustomerRegistrationServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerRegistrationService customerRegistrationService;


    private static Customer customer;
    private static final String NAME = "Lando Norris";
    private static final String USERNAME = "NOR";
    private static final String PHONE_NUMBER = "+491234";
    private static final Long ID = 0L;


    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        customer = new Customer();
        customer.setName(NAME);
        customer.setUserName(USERNAME);
        customer.setPhoneNumber(PHONE_NUMBER);
        customer.setId(ID);
    }

    @Test
    void registerNewCustomer() {
        Mockito
                .when(customerRepository.save(Mockito.any())).thenReturn(customer);
        Assertions
                .assertEquals(customerRegistrationService.registerNewCustomer(customer), customer);
    }
    
    @Test
    void failToRegisterWithExistingPhoneNumber() {
        Mockito
                .when(customerRepository.selectCustomerByPhoneNumber(customer.getPhoneNumber()))
                .thenReturn(Optional.of(customer));
        Assertions
                .assertThrows(IllegalStateException.class, () -> customerRegistrationService.registerNewCustomer(customer));
    }

    @Test
    void failToRegisterWithExistingPhoneNumberAndDifferentAttributes() {
        Customer cTmp = new Customer();
        cTmp.setName("John Doe");
        cTmp.setUserName("JD");
        cTmp.setPhoneNumber(PHONE_NUMBER);
        cTmp.setId(1L);

        Mockito
                .when(customerRepository.selectCustomerByPhoneNumber(customer.getPhoneNumber()))
                .thenReturn(Optional.of(customer));
        Assertions
                .assertThrows(BadRequestException.class, () -> customerRegistrationService.registerNewCustomer(cTmp));
    }
}
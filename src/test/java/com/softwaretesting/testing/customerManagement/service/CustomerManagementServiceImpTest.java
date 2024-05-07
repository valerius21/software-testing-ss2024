package com.softwaretesting.testing.customerManagement.service;

import com.softwaretesting.testing.dao.CustomerRepository;
import com.softwaretesting.testing.model.Customer;
import com.softwaretesting.testing.validator.CustomerValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

class CustomerManagementServiceImpTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerValidator customerValidator;

    @InjectMocks
    private CustomerManagementServiceImp customerManagementService;

    private Customer customer;
    private static final String NAME = "Lando Norris";
    private static final String USERNAME = "NOR";
    private static final String PHONE_NUMBER = "+49123";


    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        customer = new Customer();
        customer.setName(NAME);
        customer.setUserName(USERNAME);
        customer.setPhoneNumber(PHONE_NUMBER);
    }

    @Test
    void shouldTestList() {
        Mockito
                .when(customerRepository.findAll())
                .thenReturn(new ArrayList<>() {{
                    add(customer);
                }});
        Assertions
                .assertTrue(customerManagementService
                        .list()
                        .contains(customer)
                );
    }

    @Test
    void shouldTestEmptyList() {
        Mockito
                .when(customerRepository.findAll())
                .thenReturn(new ArrayList<>());
        Assertions
                .assertTrue(customerManagementService
                        .list()
                        .isEmpty()
                );
    }


    @Test
    void findByUserName() {
        Mockito
                .when(customerRepository.findByUserName(customer.getUserName()))
                .thenReturn(Optional.of(customer));
        Assertions
                .assertEquals(customer, customerManagementService.findByUserName(USERNAME));
    }

    @Test
    void findById() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    void selectCustomerByPhoneNumber() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    void delete() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    void addCustomer() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    void saveAll() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
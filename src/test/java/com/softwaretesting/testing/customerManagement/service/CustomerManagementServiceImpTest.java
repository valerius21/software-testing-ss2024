package com.softwaretesting.testing.customerManagement.service;

import com.softwaretesting.testing.customerRegistration.service.CustomerRegistrationService;
import com.softwaretesting.testing.dao.CustomerRepository;
import com.softwaretesting.testing.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

class CustomerManagementServiceImpTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerRegistrationService customerRegistrationService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customer = new Customer();
        customer.setName("Max Verstappen");
        customer.setUserName("no_mickey_no");
        customer.setPhoneNumber("+49331");
    }


    @Test
    void list() {
    }

    @Test
    void findByUserName() {
    }

    @Test
    void findById() {
    }

    @Test
    void selectCustomerByPhoneNumber() {
        var result = Mockito
                .when(customerRepository.selectCustomerByPhoneNumber(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
    }

    @Test
    void delete() {
    }

    @Test
    void addCustomer() {
    }

    @Test
    void saveAll() {
    }
}
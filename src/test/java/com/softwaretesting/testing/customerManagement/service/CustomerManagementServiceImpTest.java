package com.softwaretesting.testing.customerManagement.service;

import com.softwaretesting.testing.dao.CustomerRepository;
import com.softwaretesting.testing.exception.BadRequestException;
import com.softwaretesting.testing.exception.CustomerNotFoundException;
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

    private static final long ID = 0L;
    private static final String NAME = "Lando Norris";
    private static final String USERNAME = "NOR";
    private static final String PHONE_NUMBER = "+49123";


    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        customer = new Customer();
        customer.setId(ID);
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
        Mockito
                .when(customerRepository.findById(customer.getId()))
                .thenReturn(Optional.of(customer));

        Assertions
                .assertEquals(customer, customerManagementService.findById(ID));
    }

    @Test
    void selectCustomerByPhoneNumber() {
        Mockito
                .when(customerRepository.selectCustomerByPhoneNumber(customer.getPhoneNumber()))
                .thenReturn(Optional.of(customer));
        Assertions
                .assertEquals(customer, customerManagementService.selectCustomerByPhoneNumber(PHONE_NUMBER));
    }

    @Test
    void deleteExistingCustomer() {
        Mockito
                .when(customerRepository.existsById(customer.getId()))
                .thenReturn(true);
        Assertions
                .assertDoesNotThrow(() -> customerManagementService.delete(ID));
    }

    @Test
    void deleteAbsentCustomer() {
        Mockito
                .when(customerRepository.existsById(customer.getId()))
                .thenReturn(false);
        CustomerNotFoundException e = Assertions
                .assertThrows(CustomerNotFoundException.class, () -> customerManagementService.delete(ID));
    }

    @Test
    void confirmDeleteAbsentCustomerErrorMessage() {
        Mockito
                .when(customerRepository.existsById(customer.getId()))
                .thenReturn(false);
        CustomerNotFoundException e = Assertions
                .assertThrows(CustomerNotFoundException.class, () -> customerManagementService.delete(ID));
        Assertions
                .assertEquals("Customer with id " + ID + " does not exists", e.getMessage());
    }


    @Test
    void addCustomer() {
        Mockito
                .when(customerRepository.save(customer))
                .thenReturn(customer);
        Assertions
                .assertEquals(customer, customerManagementService.addCustomer(customer));
    }

    @Test
    void addCustomerWithExistingUserName() {
        Mockito
                .when(customerRepository.selectCustomerByPhoneNumber(customer.getPhoneNumber()))
                .thenReturn(Optional.of(customer));
        Assertions
                .assertThrows(BadRequestException.class, () -> customerManagementService.addCustomer(customer));
    }

    @Test
    void addCustomerWithExistingUserNameErrorMessage() {
        Mockito
                .when(customerRepository.selectCustomerByPhoneNumber(customer.getPhoneNumber()))
                .thenReturn(Optional.of(customer));
        BadRequestException e = Assertions
                .assertThrows(BadRequestException.class, () -> customerManagementService.addCustomer(customer));
        Assertions
                .assertEquals("Phone Number " + PHONE_NUMBER + " taken", e.getMessage());
    }

    @Test
    void saveAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customer);
        Mockito
                .when(customerRepository.saveAll(customers))
                .thenReturn(customers);
        Assertions
                .assertTrue( customerManagementService.saveAll(customers).containsAll(customers));
    }

    @Test
    void saveAllEmptyList() {
        ArrayList<Customer> customers = new ArrayList<>();
        Mockito
                .when(customerRepository.saveAll(customers))
                .thenReturn(customers);
        Assertions
                .assertTrue(customerManagementService.saveAll(customers).isEmpty());
    }
}
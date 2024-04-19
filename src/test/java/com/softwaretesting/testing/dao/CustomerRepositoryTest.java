package com.softwaretesting.testing.dao;

import com.softwaretesting.testing.model.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CustomerRepositoryTest {
    private static Customer customer;
    private static CustomerRepository customerRepository;
    private static Optional<Customer> findCustomerOptional;

    private static Optional<Customer> phoneCustomerOptional;
    private static final String username = "testUser";
    private static final String testPhoneNumber = "+4955112345";

    @BeforeAll
    public static void createMockInstance() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customer = new Customer();
        customer.setUserName(username);
        customer.setPhoneNumber(testPhoneNumber);

        when(customerRepository.findByUserName(username)).thenReturn(Optional.of(customer));
        findCustomerOptional = customerRepository.findByUserName(username);

        when(customerRepository.selectCustomerByPhoneNumber(testPhoneNumber)).thenReturn(Optional.of(customer));
        phoneCustomerOptional = customerRepository.selectCustomerByPhoneNumber(testPhoneNumber);
    }

    @Test
    public void shouldVerifyThatCustomerOptionalExists() {
        assertTrue(findCustomerOptional.isPresent());
    }

    @Test
    public void shouldFindUserByName() {
        if (findCustomerOptional.isEmpty()) {
            fail("customer should be present");
        }
        assertEquals(username, findCustomerOptional.get().getUserName());
    }

    @Test
    void shouldVerifyThatCustomerOptionalWithPhoneNumberExists() {
        assertTrue(phoneCustomerOptional.isPresent());
    }

    @Test
    void shouldSelectCustomerByPhoneNumber() {
        if (phoneCustomerOptional.isEmpty()) {
            fail("customer with phone number should be present");
        }
        assertEquals(testPhoneNumber, phoneCustomerOptional.get().getPhoneNumber());
    }
}

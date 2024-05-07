package com.softwaretesting.testing.dao;

import com.softwaretesting.testing.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    private Customer getDummyCustomer() {
        return new Customer(
                0L,
                "NOR",
                "Lando Norris",
                "+405512312451");
    }

    @Test
    void createOne() {
        final Customer insertedCustomer = customerRepository.save(getDummyCustomer());
        final Optional<Customer> customerById = customerRepository.findById(insertedCustomer.getId());
        assertTrue(customerById.isPresent());
        assertEquals(insertedCustomer, customerById.get());
    }

    @Test
    void addDeleteCheck() {
        final Customer insertedCustomer = customerRepository.save(getDummyCustomer());
        assertTrue(customerRepository.existsById(insertedCustomer.getId()));

        customerRepository.deleteById(insertedCustomer.getId());
        assertFalse(customerRepository.existsById(insertedCustomer.getId()));
    }

    @Test
    void findByName() {
        final String userName = "testuser";
        final Customer customerWithUserName = getDummyCustomer();
        customerWithUserName.setUserName(userName);

        final Customer insertedCustomer = customerRepository.save(customerWithUserName);
        final Optional<Customer> customerByUserName = customerRepository.findByUserName(userName);

        assertTrue(customerByUserName.isPresent());
        assertEquals(insertedCustomer, customerByUserName.get());
    }

    @Test
    void findByPhoneNumber() {
        final String phoneNumber = "+49123412";
        final Customer customerWithPhoneNumber = getDummyCustomer();
        customerWithPhoneNumber.setPhoneNumber(phoneNumber);

        final Customer insertedCustomer = customerRepository.save(customerWithPhoneNumber);
        final Optional<Customer> customerByPhoneNumber = customerRepository.selectCustomerByPhoneNumber(phoneNumber);

        assertTrue(customerByPhoneNumber.isPresent());
        assertEquals(insertedCustomer, customerByPhoneNumber.get());
    }


    @Test
    void deleteMany() {
        final List<Customer> customerList = List.of(getDummyCustomer(), getDummyCustomer(), getDummyCustomer());
        final Iterable<Customer> insertedCustomerList = customerRepository.saveAll(customerList);

        final Iterable<Customer> allCustomerList = customerRepository.findAll();

        assertEquals(insertedCustomerList, allCustomerList);

        customerRepository.deleteAll(insertedCustomerList);
        assertEquals(0, customerRepository.count());
    }

    @Test
    void deleteAll() {
        final List<Customer> customerList = List.of(getDummyCustomer(), getDummyCustomer(), getDummyCustomer());
        final Iterable<Customer> insertedCustomerList = customerRepository.saveAll(customerList);

        final Iterable<Customer> allCustomerList = customerRepository.findAll();

        assertEquals(insertedCustomerList, allCustomerList);

        customerRepository.deleteAll();
        assertEquals(0, customerRepository.count());
    }

    @Test
    void aggregate() {
        final List<Customer> customerList = List.of(getDummyCustomer(), getDummyCustomer(), getDummyCustomer());
        customerRepository.saveAll(customerList);

        assertEquals(customerList.size(), customerRepository.count());
    }
}

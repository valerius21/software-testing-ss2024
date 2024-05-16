package com.softwaretesting.testing.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer customerA;

    private Customer createCustomer(Customer customer) {
        Customer tCustomer = new Customer();
        tCustomer.setId(customer.getId());
        tCustomer.setUserName(customer.getUserName());
        tCustomer.setName(customer.getName());
        tCustomer.setPhoneNumber(customer.getPhoneNumber());
        return tCustomer;
    }

    @BeforeEach
    void init() {
        customerA = new Customer();
        customerA.setId(1L);
        customerA.setUserName("userName");
        customerA.setName("name");
        customerA.setPhoneNumber("phoneNumber");
    }

    @Test
    void testIdentity() {
        Object o = customerA;
        assertEquals(customerA, o);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, customerA);
    }

    @Test
    void testEqualsByCopy() {
        Customer customerB = createCustomer(customerA);
        assertEquals(customerA, customerB);
    }

    @Test
    void testEqualsWithSameCustomer() {
        Customer sameCustomer = createCustomer(customerA);
        assertEquals(customerA, sameCustomer);
    }

    @Test
    void testNotEqualsWithDifferentId() {
        Customer differentCustomer = createCustomer(customerA);
        differentCustomer.setId(2L);
        assertNotEquals(customerA, differentCustomer);
    }

    @Test
    void testNotEqualsWithNull() {
        Customer nullCustomer = null;
        assertNotEquals(customerA, nullCustomer, "not equals with null");
    }

    @Test
    void testNotEqualsWithDifferentClassObject() {
        assertNotEquals(customerA, new Object());
    }

    @Test
    void testNotEqualsWithDifferentUserName() {
        Customer differentCustomer = createCustomer(customerA);
        differentCustomer.setUserName("differentUserName");
        assertNotEquals(customerA, differentCustomer);
    }

    @Test
    void testNotEqualsWithSameIdDifferentName() {
        Customer differentCustomer = createCustomer(customerA);
        differentCustomer.setName("differentName");
        assertNotEquals(customerA, differentCustomer);
    }

    @Test
    void testNotEqualsWithDifferentPhoneNumber() {
        Customer differentCustomer = createCustomer(customerA);
        differentCustomer.setPhoneNumber("differentPhoneNumber");
        assertNotEquals(customerA, differentCustomer);
    }

    @Test
    void equalsObjectById() {
        Customer innerCustomer = createCustomer(customerA);
        assertEquals(customerA, innerCustomer);
    }

    @Test
    void testEqualsDifferentId() {
        Customer customerB = createCustomer(customerA);
        customerB.setId(2L);
        assertNotEquals(customerA, customerB);
    }

    @Test
    void testHashCode() {
        assertEquals(customerA.hashCode(), customerA.hashCode());
    }

    @Test
    void testHashCodeNotZero() {
        assertNotEquals(0, customerA.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(customerA.toString(), customerA.toString());
    }

    @Test
    void testToStringNotEmpty() {
        assertNotEquals("", customerA.toString());
    }

    @Test
    void testConstructorId() {
        Long id = customerA.getId();
        String userName = customerA.getUserName();
        String name = customerA.getName();
        String phoneNumber = customerA.getPhoneNumber();

        Customer customer = new Customer(
                id,
                userName,
                name,
                phoneNumber
                        );

        assertEquals(id, customer.getId());
    }

    @Test
    void testConstructorUserName() {
        Long id = customerA.getId();
        String userName = customerA.getUserName();
        String name = customerA.getName();
        String phoneNumber = customerA.getPhoneNumber();

        Customer customer = new Customer(
                id,
                userName,
                name,
                phoneNumber
        );

        assertEquals(userName, customer.getUserName());
    }

    @Test
    void testConstructorName() {
        Long id = customerA.getId();
        String userName = customerA.getUserName();
        String name = customerA.getName();
        String phoneNumber = customerA.getPhoneNumber();

        Customer customer = new Customer(
                id,
                userName,
                name,
                phoneNumber
        );

        assertEquals(name, customer.getName());
    }

    @Test
    void testConstructorPhoneNumber() {
        Long id = customerA.getId();
        String userName = customerA.getUserName();
        String name = customerA.getName();
        String phoneNumber = customerA.getPhoneNumber();

        Customer customer = new Customer(
                id,
                userName,
                name,
                phoneNumber
        );

        assertEquals(phoneNumber, customer.getPhoneNumber());
    }
}
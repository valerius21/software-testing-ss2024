package com.softwaretesting.testing.customerRegistration.controller;

import com.softwaretesting.testing.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String CUSTOMER_REG_URL = "/api/v1/customer-registration";


    private Customer makeCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUserName("NOR");
        customer.setName("Lando Norris");
        customer.setPhoneNumber("+491234");
        return customer;
    }

    private String toJson(Customer customer) {
        return "{\n" +
                "  \"id\": " + customer.getId() + ",\n" +
                "  \"userName\": \"" + customer.getUserName() + "\",\n" +
                "  \"name\": \"" + customer.getName() + "\",\n" +
                "  \"phoneNumber\": \"" + customer.getPhoneNumber() + "\"\n" +
                "}";
    }

    @AfterEach
    void removeCustomers() throws Exception {
        // clean everything up... just in case.
        for (int i = 0; i < 100; i++) {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/2000" + i));
        }
    }

    @Test
    void registerNewCustomer() throws Exception {
        final Customer customer = makeCustomer();

        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_REG_URL)
                        .content(toJson(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("NOR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lando Norris"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("+491234"));
    }

    @Test
    void failsWithoutUserName() throws Exception {
        final Customer customer = makeCustomer();
        customer.setUserName("");

        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_REG_URL)
                        .content(toJson(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void failsWithoutPhoneNumber() throws Exception {
        final Customer customer = makeCustomer();
        customer.setPhoneNumber("");

        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_REG_URL)
                        .content(toJson(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void failsWithoutName() throws Exception {
        final Customer customer = makeCustomer();
        customer.setName("");

        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_REG_URL)
                        .content(toJson(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void failsWithInvalidPhoneNumber() throws Exception {
        final Customer customer = makeCustomer();
        customer.setPhoneNumber("hallo");

        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_REG_URL)
                        .content(toJson(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void registerNewCustomerTwiceTest() throws Exception {
        final Customer customer = makeCustomer();

        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_REG_URL)
                        .content(toJson(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("NOR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lando Norris"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("+491234"));

        // FIXME: needs sufficient exception handling inside the handler
        // mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_REG_URL)
        //                        .content(toJson(customer))
        //                        .contentType(MediaType.APPLICATION_JSON))
        //                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}
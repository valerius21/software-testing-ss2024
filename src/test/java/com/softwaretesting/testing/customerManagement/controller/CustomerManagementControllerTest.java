package com.softwaretesting.testing.customerManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwaretesting.testing.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerManagementControllerTest {
    private int lastId = 20_000 - 1;

    @Autowired
    private MockMvc mockMvc;

    private final String CUSTOMER_URL = "/api/v1/customers";

    private Customer makeCustomer() {
        Customer customer = new Customer();
        customer.setId(lastId + 1L);
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
            mockMvc.perform(MockMvcRequestBuilders.delete(CUSTOMER_URL + "/2000" + i));
        }
    }

    @Test
    void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_URL + "/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").exists());
    }


    @Test
    void addCustomer() throws Exception {
        String payload = toJson(this.makeCustomer());
        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("NOR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lando Norris"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("+491234"));
    }

    @Test
    void getById() throws Exception {
        String payload = toJson(this.makeCustomer());
        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(mvcResult -> {
                    var res = mvcResult.getResponse();
                    var body = res.getContentAsString();
                    var split = body.split(",");
                    var first = split[0];
                    var split2 = first.split(":");
                    var second = split2[1];
                    var id = Integer.parseInt(second);

                    mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_URL + "/" + id ))
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("NOR"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lando Norris"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("+491234"));
                });
    }

    @Test
    void getByIdNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_URL + "/9999999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void addCustomerDouble() throws Exception {
        String payload = toJson(this.makeCustomer());
        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("NOR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lando Norris"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("+491234"));

        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(CUSTOMER_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(CUSTOMER_URL + "/9999999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void tryToAddCustomerWithoutName() throws Exception {
        String payload = toJson(this.makeCustomer());
        payload = payload.replace("Lando Norris", "");
        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void tryToAddCustomerWithoutPhoneNumber() throws Exception {
        String payload = toJson(this.makeCustomer());
        payload = payload.replace("+491234", "");
        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void tryToAddCustomerWithoutUserName() throws Exception {
        String payload = toJson(this.makeCustomer());
        payload = payload.replace("NOR", "");
        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void tryToAddCustomerInvalidPhoneNumber() throws Exception {
        String payload = toJson(this.makeCustomer());
        payload = payload.replace("+491234", "hallo");
        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
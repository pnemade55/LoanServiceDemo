package com.ing.ls.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.ls.controller.CustomerController;
import com.ing.ls.entity.Customer;
import com.ing.ls.service.CustomerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;


    ArrayList<Customer> mockCustomers= new ArrayList<>();
    @BeforeEach
    public void setup(){
        mockCustomers.add(new Customer(123L, "John Rao", new ArrayList<>()));
        mockCustomers.add(new Customer(234L, "Steve Smith", new ArrayList<>()));
    }

    @Test
    @Order(1)
    public void fetchAllCustomers() throws Exception{
        // precondition
        given(customerService.getAllCustomerList()).willReturn(mockCustomers);

        // action
        ResultActions response = mockMvc.perform(get("/v1/customer"));

        // verify
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].customerId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].customerId").value(equalTo(mockCustomers.get(0).getCustomerId()),Long.class))
                .andExpect(jsonPath("$.[0].customerName",
                        is(mockCustomers.get(0).getCustomerName())))
                .andExpect(jsonPath("$.[0].loans.[*]",
                        is(mockCustomers.get(0).getLoans())));
    }

}

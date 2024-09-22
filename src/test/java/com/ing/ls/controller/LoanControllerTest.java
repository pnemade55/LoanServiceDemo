package com.ing.ls.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.ls.dto.LoanRequest;
import com.ing.ls.dto.LoanSummary;
import com.ing.ls.dto.ResponseStatus;
import com.ing.ls.entity.Customer;
import com.ing.ls.entity.Loan;
import com.ing.ls.service.LoanService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @Autowired
    private ObjectMapper objectMapper;

    Customer mockCustomers= null;

    @BeforeEach
    public void setup(){
        ArrayList<Loan> loans= new ArrayList<>();
        loans.add(new Loan(UUID.randomUUID(),10000));
        mockCustomers= new Customer(123L, "John Rao", loans);
    }

    @Test
    @Order(1)
    public void applyLoan() throws Exception{
        // precondition
        given(loanService.applyLoan(any(Customer.class))).willReturn(mockCustomers);

        //request
        ArrayList<Loan> loans= new ArrayList<>();
        loans.add(new Loan(UUID.randomUUID(),10000));
        LoanRequest  loanRequest= new LoanRequest(new Customer(123L, "John Rao", loans));


        // action
        ResultActions response = mockMvc.perform(post("/v1/loan/apply")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loanRequest)));

        // verify
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").exists())
                .andExpect(jsonPath("$.customerName",
                            is(mockCustomers.getCustomerName())))
                    .andExpect(jsonPath("$.loans.[0].loanAmount").value(equalTo(mockCustomers.getLoans().get(0).getLoanAmount()), Long.class));
    }


    @Test
    @Order(1)
    public void getLoanSummary() throws Exception {

        given(loanService.getLoanSummary(any(Long.class))).willReturn(new LoanSummary(123L, "John Rao", 10000, new ResponseStatus()));

        long customerid= 123L;
        // action
        ResultActions response = mockMvc.perform(get("/v1/loan/summary/{customerId}",customerid));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.customerId").value(equalTo(123L),Long.class))
                .andExpect(jsonPath("$.customerName", is("John Rao")))
                .andExpect(jsonPath("$.totalLoanApplied").value(equalTo(10000L),Long.class));

    }
/*
    {
        "customerId": 1,
            "customerName": "pallavi Kinge",
            "totalLoanApplied": 2412657
    }*/


}

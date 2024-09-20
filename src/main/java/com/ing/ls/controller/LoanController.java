package com.ing.ls.controller;

import com.ing.ls.dto.LoanRequest;
import com.ing.ls.dto.LoanSummary;
import com.ing.ls.entity.Customer;
import com.ing.ls.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/loan")
public class LoanController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/apply")
    public Customer SaveCustomer(@RequestBody LoanRequest loanRequest){
        return customerService.saveCustomer(loanRequest.getCustomer());
    }

    @GetMapping("/summary/{customerId}")
    public LoanSummary getLoanSummary(@PathVariable long customerId){

        return customerService.getLoanSummary(customerId);
    }

    @GetMapping("/customers")
    public List<Customer> fetchAllCustomers(){
        return customerService.getAllCustomerList();
    }

}

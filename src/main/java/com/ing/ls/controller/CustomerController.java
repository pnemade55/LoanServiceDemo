package com.ing.ls.controller;

import com.ing.ls.entity.Customer;
import com.ing.ls.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>>  fetchAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomerList(), HttpStatus.OK);
    }

}

package com.ing.ls.controller;

import com.ing.ls.dto.LoanRequest;
import com.ing.ls.dto.LoanSummary;
import com.ing.ls.entity.Customer;
import com.ing.ls.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<Customer> applyLoan(@Valid @RequestBody LoanRequest loanRequest){
        Customer customer= loanService.applyLoan(loanRequest.getCustomer());
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/summary/{customerId}")
    public ResponseEntity<LoanSummary> getLoanSummary(@PathVariable long customerId){
        return new ResponseEntity<>(loanService.getLoanSummary(customerId), HttpStatus.OK);
    }

}

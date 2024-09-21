package com.ing.ls.service.impl;

import com.ing.ls.dao.CustomerRepository;
import com.ing.ls.dao.LoanRepository;
import com.ing.ls.entity.Customer;
import com.ing.ls.entity.Loan;
import com.ing.ls.exception.CustomerNotFoundException;
import com.ing.ls.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Loan saveLoanRequest(Loan loan) {

       return loanRepository.save(loan);
    }

    public List<Loan> getLoanByCustomerId(Long customerId){
        Customer cust = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with CustomerId"+customerId + " not found!"));

        return cust.getLoans();
    }

    @Override
    public List<Loan> getAllLoan() {
        return loanRepository.findAll();
    }
}

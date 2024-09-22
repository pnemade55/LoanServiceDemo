package com.ing.ls.service;

import com.ing.ls.dto.LoanSummary;
import com.ing.ls.entity.Customer;
import com.ing.ls.entity.Loan;

import java.util.List;

public interface LoanService {

    Customer applyLoan(Customer customer);

    LoanSummary getLoanSummary(long customerId);

    List<Loan> getLoanByCustomerId(Long customerId);

}

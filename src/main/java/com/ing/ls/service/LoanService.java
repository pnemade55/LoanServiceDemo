package com.ing.ls.service;

import com.ing.ls.entity.Loan;

import java.util.List;

public interface LoanService {

    Loan saveLoanRequest(Loan loan);

    List<Loan> getAllLoan();

    List<Loan> getLoanByCustomerId(Long customerId);

}

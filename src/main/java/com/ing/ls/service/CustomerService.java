package com.ing.ls.service;

import com.ing.ls.dto.LoanSummary;
import com.ing.ls.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomerList();

    LoanSummary getLoanSummary(long customerId);
}


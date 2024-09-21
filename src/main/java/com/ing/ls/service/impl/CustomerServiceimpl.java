package com.ing.ls.service.impl;

import com.ing.ls.dao.CustomerRepository;
import com.ing.ls.dto.LoanSummary;
import com.ing.ls.entity.Customer;
import com.ing.ls.entity.Loan;
import com.ing.ls.exception.CustomerNotFoundException;
import com.ing.ls.service.CustomerService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceimpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        Customer custExisting = customerRepository.findByCustomerIdOrCustomerName(customer.getCustomerId(), customer.getCustomerName());
        Customer updatedCust;

        if(custExisting != null){
            if(validateCustomer(custExisting,customer)){
                updatedCust= custExisting.addLoans(customer.getLoans());
            }else{
                throw new CustomerNotFoundException("Requested CustomerId : "+customer.getCustomerId() +
                        "and Customer Name: "+ customer.getCustomerName() +"\n missmatch found !  Kindly Apply with Correct combination of CustomerId and Customer Name");
            }
           return customerRepository.save(updatedCust);
        }
            return customerRepository.save(customer);
    }

    private boolean validateCustomer(Customer custExisting, Customer requestedCustomer) {

        return (requestedCustomer.getCustomerId() == null || Objects.equals(custExisting.getCustomerId(), requestedCustomer.getCustomerId())) &&
                (StringUtils.isBlank(requestedCustomer.getCustomerName()) || custExisting.getCustomerName().equals(requestedCustomer.getCustomerName()));
    }

    @Override
    public List<Customer> getAllCustomerList() {
        return customerRepository.findAll();
    }

    @Override
    public LoanSummary getLoanSummary(long customerId) {

        Customer customer = customerRepository.findById(customerId)
                                            .orElseThrow(() -> new CustomerNotFoundException("Requested CustomerId: " + customerId +" doesnt exist !"));

        long totalLoanApplied = customer.getLoans()
                                .stream()
                                .mapToLong(Loan::getLoanAmount)
                                .sum();

        LoanSummary loanSummary = new LoanSummary();
        loanSummary.setTotalLoanApplied(totalLoanApplied);

        loanSummary.setCustomerId(customer.getCustomerId());
        loanSummary.setCustomerName(customer.getCustomerName());

        return loanSummary;
    }


}

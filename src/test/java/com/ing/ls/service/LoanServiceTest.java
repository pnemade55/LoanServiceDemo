package com.ing.ls.service;

import com.ing.ls.dao.CustomerRepository;
import com.ing.ls.dto.LoanSummary;
import com.ing.ls.entity.Customer;
import com.ing.ls.entity.Loan;
import com.ing.ls.exception.CustomerNotFoundException;
import com.ing.ls.service.impl.LoanServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoanServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    @Order(1)
    public void applyLoan(){
        Customer cust1 = new Customer(123L, "John Rao", new ArrayList<>());

        // precondition
        given(customerRepository.findByCustomerIdOrCustomerName(any(Long.class), any(String.class))).willReturn(cust1);
        given(customerRepository.save(any(Customer.class))).willReturn(cust1);

        // action
        Customer responseCustomer= loanService.applyLoan(new Customer(123L, "John Rao", new ArrayList<>()));

        // verify
        System.out.println(responseCustomer);
        assertThat(responseCustomer).isNotNull();
        assertThat(responseCustomer.getCustomerId()).isEqualTo(123L);
        assertThat(responseCustomer.getCustomerName()).isEqualTo("John Rao");
        assertThat(responseCustomer.getLoans().size()).isEqualTo(0);

    }

    @Test
    @Order(2)
    public void getLoanSummary(){
        ArrayList<Loan> loans= new ArrayList<>();
        loans.add(new Loan(UUID.randomUUID(),10000));
        loans.add(new Loan(UUID.randomUUID(),50000));
        Customer cust1 = new Customer(123L, "John Rao", loans);

        // precondition
        given(customerRepository.findById(any(Long.class))).willReturn(Optional.of(cust1));

        // action
        LoanSummary responseLoanSummary= loanService.getLoanSummary(123L);

        // verify
        System.out.println(responseLoanSummary);
        assertThat(responseLoanSummary).isNotNull();
        assertThat(responseLoanSummary.getCustomerId()).isEqualTo(123L);
        assertThat(responseLoanSummary.getCustomerName()).isEqualTo("John Rao");
        assertThat(responseLoanSummary.getTotalLoanApplied()).isEqualTo(60000);

    }

}
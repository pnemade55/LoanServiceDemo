package com.ing.ls.dao;

import com.ing.ls.entity.Customer;
import com.ing.ls.entity.Loan;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRepositoryTest {


    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void findByCustomerIdOrCustomerName(){
        Customer customer= new Customer(); customer.setCustomerName("John Rao");
        customerRepository.save(customer);

        //Action
        Customer fetchedCustomer = customerRepository.findByCustomerIdOrCustomerName(1L, "John Rao");

        //Verify
        System.out.println(fetchedCustomer);
        assertThat(fetchedCustomer).isNotNull();
        assertThat(fetchedCustomer.getCustomerId()).isEqualTo(1L);
        assertThat(fetchedCustomer.getCustomerName()).isEqualTo("John Rao");

    }



}

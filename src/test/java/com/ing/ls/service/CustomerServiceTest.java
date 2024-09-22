package com.ing.ls.service;


import com.ing.ls.dao.CustomerRepository;
import com.ing.ls.entity.Customer;
import com.ing.ls.service.impl.CustomerServiceimpl;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceimpl customerService;

    @Test
    @Order(1)
    public void getAllEmployee(){
        Customer cust1 = new Customer(123L, "John Rao", new ArrayList<>());
        Customer cust2 = new Customer(234L, "Rohit Sharma", new ArrayList<>());

        // precondition
        given(customerRepository.findAll()).willReturn(List.of(cust1,cust2));

        // action
        List<Customer> customerList = customerService.getAllCustomerList();

        // verify
        System.out.println(customerList);
        assertThat(customerList).isNotNull();
        assertThat(customerList.size()).isGreaterThan(1);
    }

}
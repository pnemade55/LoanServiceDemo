package com.ing.ls.service.impl;

import com.ing.ls.dao.CustomerRepository;
import com.ing.ls.entity.Customer;
import com.ing.ls.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceimpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomerList() {
        return customerRepository.findAll();
    }

}

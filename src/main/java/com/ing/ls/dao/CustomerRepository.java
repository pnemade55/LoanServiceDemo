package com.ing.ls.dao;

import com.ing.ls.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCustomerIdOrCustomerName(Long customerId, String customerName);
}

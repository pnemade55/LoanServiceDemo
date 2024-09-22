package com.ing.ls;

import com.ing.ls.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repo extends JpaRepository<Customer,Long> {
}
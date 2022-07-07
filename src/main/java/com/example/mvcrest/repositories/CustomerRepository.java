package com.example.mvcrest.repositories;

import com.example.mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByLastName(String lastName);
}

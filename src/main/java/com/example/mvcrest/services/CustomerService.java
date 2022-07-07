package com.example.mvcrest.services;

import com.example.mvcrest.api.v1.mapper.CustomerMapper;
import com.example.mvcrest.api.v1.model.CustomerDTO;
import com.example.mvcrest.repositories.CustomerRepository;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long customerId) throws Exception;

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    void setCustomerMapper(CustomerMapper customerMapper);

    void setCustomerRepository(CustomerRepository customerRepository);
}

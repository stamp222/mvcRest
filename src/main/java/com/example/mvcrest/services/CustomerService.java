package com.example.mvcrest.services;

import com.example.mvcrest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long customerId) throws Exception;

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long customerId, CustomerDTO customerDTO);

    CustomerDTO patchCustomerByDTO(Long customerId, CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}

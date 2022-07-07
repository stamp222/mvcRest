package com.example.mvcrest.services;

import com.example.mvcrest.api.v1.mapper.CustomerMapper;
import com.example.mvcrest.api.v1.model.CustomerDTO;
import com.example.mvcrest.domain.Customer;
import com.example.mvcrest.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{


    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;


    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }


    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }
    @Override
    public CustomerDTO getCustomerById(Long customerId) throws Exception {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        return customerOptional.map(customer -> {
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
            return customerDTO;
        }).orElseThrow(() -> new Exception("Custoemr not found - " + customerId));
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        Customer save = customerRepository.save(customer);
        CustomerDTO customerDTO1 = customerMapper.customerToCustomerDTO(save);
        customerDTO1.setCustomerUrl("/api/v1/customer/" + save.getId());
        return customerDTO1;
    }
}

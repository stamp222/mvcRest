package com.example.mvcrest.services;

import com.example.mvcrest.ResourceNotFoundException;
import com.example.mvcrest.api.v1.mapper.CustomerMapper;
import com.example.mvcrest.api.v1.model.CustomerDTO;
import com.example.mvcrest.controllers.v1.CustomerController;
import com.example.mvcrest.domain.Customer;
import com.example.mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{


    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }
    @Override
    public CustomerDTO getCustomerById(Long customerId) throws Exception {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        return customerOptional.map(customer -> {
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTO.setCustomerUrl(getCustomerUrl(customerId));
            return customerDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDTOtoCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer save = customerRepository.save(customer);
        CustomerDTO customerDTO1 = customerMapper.customerToCustomerDTO(save);
        customerDTO1.setCustomerUrl(getCustomerUrl(save.getId()));
        return customerDTO1;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long customerId, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        customer.setId(customerId);
        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomerByDTO(Long customerId, CustomerDTO customerDTO) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    if (customerDTO.getFirstName() != null) {
                        customer.setFirstName(customerDTO.getFirstName());
                    }
                    if(customerDTO.getLastName() != null) {
                        customer.setLastName(customerDTO.getLastName());
                    }
                    CustomerDTO customerDTO1 = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
                    customerDTO1.setCustomerUrl(getCustomerUrl(customerId));
                    return customerDTO1;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    public String getCustomerUrl(Long id) {
        return CustomerController.API_V_1_CUSTOMERS + "/" + id;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }


}

package com.example.mvcrest.services;

import com.example.mvcrest.api.v1.mapper.CustomerMapper;
import com.example.mvcrest.api.v1.model.CustomerDTO;
import com.example.mvcrest.domain.Customer;
import com.example.mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService; // zamiast inicjalizacji w setUp

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setUp() {
//        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    void getAllCustomers() {
        List<Customer> customerList = Arrays.asList(new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> customersDTOs = customerService.getAllCustomers();

        assertEquals(2L, customersDTOs.size());
    }

    @Test
    void getCustomerById() throws Exception {
        Customer cus = new Customer();
        cus.setId(1L);
        cus.setFirstName("Jacek");
        cus.setLastName("Szyper");
        Optional<Customer> customerOptional = Optional.of(cus);

        when(customerRepository.findById(anyLong())).thenReturn(customerOptional);

        CustomerDTO customerById = customerService.getCustomerById(1L);

        assertEquals(1L, customerById.getId());
        assertEquals("Jacek", customerById.getFirstName());
    }

    @Test
    void createNewCustomerTest() throws Exception {} {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jacob");
        customerDTO.setLastName("Tarantinto");

        Customer cus = new Customer();
        cus.setId(1L);
        cus.setFirstName("Jacek");
        cus.setLastName("Szyper");

        //TODO fix
//            when(customerRepository.save(any(Customer.class))).thenReturn(cus);
//
//        CustomerDTO newCustomer = customerService.createNewCustomer(customerDTO);
//
//        assertEquals(customerDTO.getFirstName(), newCustomer.getFirstName());
//        assertEquals("/api/v1/customers/1", newCustomer.getCustomerUrl());
    }

    @Test
    void saveCustomerByDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jacob");
        customerDTO.setLastName("Tarantinto");

        Customer cus = new Customer();
        cus.setId(1L);
        cus.setFirstName("Jacek");
        cus.setLastName("Szyper");

        //TODO fix
//        when(customerRepository.save(any(Customer.class))).thenReturn(cus);
//
//        CustomerDTO savedCustomer = customerService.saveCustomerByDTO(1L, customerDTO);
//
//        assertEquals(customerDTO.getFirstName(), savedCustomer.getFirstName());
//        assertEquals("/api/v1/customers/1", savedCustomer.getCustomerUrl());
    }
}
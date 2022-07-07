package com.example.mvcrest.api.v1.mapper;

import com.example.mvcrest.api.v1.model.CustomerDTO;
import com.example.mvcrest.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    CustomerMapper mapper = CustomerMapper.INSTANCE;


    @Test
    void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Jacek");
        customer.setLastName("Szyper");

        CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);

        assertEquals(Long.valueOf(1L), customerDTO.getId());
        assertEquals("Jacek", customerDTO.getFirstName());
        assertEquals("Szyper", customerDTO.getLastName());
    }
}
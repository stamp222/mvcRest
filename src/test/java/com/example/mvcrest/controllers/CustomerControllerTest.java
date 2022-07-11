package com.example.mvcrest.controllers;

import com.example.mvcrest.ResourceNotFoundException;
import com.example.mvcrest.api.v1.model.CustomerDTO;
import com.example.mvcrest.controllers.v1.CustomerController;
import com.example.mvcrest.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest extends AbstractRestControllerTest{


    MockMvc mockMvc;

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(RestResponseEntityExceptionHandler.class)
                .build();
    }

    @Test
    void getAllCustomerss() throws Exception {
        CustomerDTO cus1 = new CustomerDTO();
        cus1.setFirstName("Jacek");
        cus1.setLastName("Szyper");
        cus1.setCustomerUrl("/api/v1/customers/1");

        CustomerDTO cus2 = new CustomerDTO();
        cus2.setFirstName("Magda");
        cus2.setLastName("Ptasik");
        cus2.setCustomerUrl("/api/v1/customers/2");

        List<CustomerDTO> customerList = Arrays.asList(cus1, cus2);

        when(customerService.getAllCustomers()).thenReturn(customerList);

        mockMvc.perform(get("/api/v1/customers/")
                        .accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void getCustomerById() throws Exception {

        CustomerDTO cus1 = new CustomerDTO();
        cus1.setFirstName("Jacek");
        cus1.setLastName("Szyper");
        cus1.setCustomerUrl("/api/v1/customers/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(cus1);

        mockMvc.perform(get("/api/v1/customers/1")
                        .accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",  equalTo("Jacek")));
    }
    @Test
    void getCustomerByIdNotFound() throws Exception {


        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/api/v1/customers/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createNewCustomerTest() throws Exception {
        CustomerDTO cus1 = new CustomerDTO();
        cus1.setFirstName("Jacek");
        cus1.setLastName("Szyper");

        CustomerDTO cus1Returned = new CustomerDTO();
        cus1Returned.setFirstName("Jacek");
        cus1Returned.setLastName("Szyper");
        cus1Returned.setCustomerUrl("/api/v1/customers/1");

        when(customerService.createNewCustomer(cus1)).thenReturn(cus1Returned);

        mockMvc.perform(post(CustomerController.API_V_1_CUSTOMERS)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cus1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Jacek")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));


//        mockMvc.perform(post("/api/v1/customers/")
//                        .contentType(APPLICATION_JSON)
//                        .content(asJsonString(cus1)))
//                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void TestUpdateCustomer() throws Exception {
        CustomerDTO cus1 = new CustomerDTO();
        cus1.setFirstName("Jacek");
        cus1.setLastName("Szyper");

        CustomerDTO cus1Returned = new CustomerDTO();
        cus1Returned.setFirstName("Jacek");
        cus1Returned.setLastName("Szyper");
        cus1Returned.setCustomerUrl("/api/v1/customers/1");

        when(customerService.saveCustomerByDTO(anyLong(),any(CustomerDTO.class))).thenReturn(cus1Returned);

        mockMvc.perform(put("/api/v1/customers/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cus1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Jacek")))
                .andExpect(jsonPath("$.lastName", equalTo("Szyper")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }

    @Test
    void testPatchCustomers() throws Exception {
        CustomerDTO cus1 = new CustomerDTO();
        cus1.setFirstName("Jacek");

        CustomerDTO cus1Returned = new CustomerDTO();
        cus1Returned.setFirstName(cus1.getFirstName());
        cus1Returned.setLastName("Szyper");
        cus1Returned.setCustomerUrl("/api/v1/customers/1");

        when(customerService.patchCustomerByDTO(anyLong(),any(CustomerDTO.class))).thenReturn(cus1Returned);

        mockMvc.perform(patch("/api/v1/customers/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cus1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Jacek")))
                .andExpect(jsonPath("$.lastName", equalTo("Szyper")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }

    @Test
    void deleteCustomerTest() throws Exception {
        mockMvc.perform(delete("/api/v1/customers/1")
                        .accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }
}
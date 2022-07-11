package com.example.mvcrest.controllers;

import com.example.mvcrest.ResourceNotFoundException;
import com.example.mvcrest.api.v1.model.VendorDTO;
import com.example.mvcrest.api.v1.model.VendorListDTO;
import com.example.mvcrest.controllers.v1.VendorController;
import com.example.mvcrest.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {VendorController.class})
class VendorControllerTest extends AbstractRestControllerTest{


    @Autowired
    MockMvc mockMvc;

    @MockBean
    VendorService vendorService;

    VendorDTO vendorDTO1;
    VendorDTO vendorDTO2;

    @BeforeEach
    void setUp() {
        vendorDTO1 = new VendorDTO("Vendor 1", VendorController.API_V_1_CUSTOMERS + "/1");
        vendorDTO2 = new VendorDTO("Vendor 2", VendorController.API_V_1_CUSTOMERS + "/2");
    }

    @Test
    void getAllVendors() throws Exception {

        VendorListDTO vendorList = new VendorListDTO(Arrays.asList(vendorDTO1, vendorDTO2));

//        when(vendorService.getAllVendors()).thenReturn(new VendorListDTO(vendorList));

        given(vendorService.getAllVendors()).willReturn(vendorList);

        mockMvc.perform(get("/api/v1/vendors/")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    void getVendorById() throws Exception {

        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO1);

        mockMvc.perform(get("/api/v1/vendors/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",  equalTo("Vendor 1")));
    }
    @Test
    void getVendorByIdNotFound() throws Exception {


        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/api/v1/vendors/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createNewVendorTest() throws Exception {
        VendorDTO cus1 = new VendorDTO();
        cus1.setName("Vendor 1");

        when(vendorService.createNewVendor(cus1)).thenReturn(vendorDTO1);

        mockMvc.perform(post("/api/v1/vendors/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cus1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Vendor 1")))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));


//        mockMvc.perform(post("/api/v1/vendors/")
//                        .contentType(APPLICATION_JSON)
//                        .content(asJsonString(cus1)))
//                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void TestUpdateVendor() throws Exception {
        VendorDTO cus1 = new VendorDTO();
        cus1.setName("Vendor 1");

        when(vendorService.saveVendorByDTO(anyLong(),any(VendorDTO.class))).thenReturn(vendorDTO1);

        mockMvc.perform(put("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cus1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Vendor 1")))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));
    }

    @Test
    void testPatchVendors() throws Exception {


        when(vendorService.patchVendorByDTO(anyLong(),any(VendorDTO.class))).thenReturn(vendorDTO1);

        mockMvc.perform(patch("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Vendor 1")))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));
    }

    @Test
    void deleteVendorTest() throws Exception {
        mockMvc.perform(delete("/api/v1/vendors/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());
        verify(vendorService).deleteVendorById(anyLong());
    }
}
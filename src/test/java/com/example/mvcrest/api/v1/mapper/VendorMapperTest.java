package com.example.mvcrest.api.v1.mapper;

import com.example.mvcrest.api.v1.model.VendorDTO;
import com.example.mvcrest.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendorMapperTest {

    VendorMapper mapper = VendorMapper.INSTANCE;


    @Test
    void vendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Apple");

        VendorDTO vendorDTO = mapper.vendorToVendorDTO(vendor);

        assertEquals("Apple", vendorDTO.getName());
    }

    @Test
    void vendorDTOtoVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Apple");

        Vendor vendor = mapper.vendorDTOtoVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}
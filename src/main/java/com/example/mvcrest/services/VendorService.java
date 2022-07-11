package com.example.mvcrest.services;

import com.example.mvcrest.api.v1.model.VendorDTO;
import com.example.mvcrest.api.v1.model.VendorListDTO;

public interface VendorService {
    VendorListDTO getAllVendors();
    VendorDTO getVendorById(Long vendorId) throws Exception;

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long vendorId, VendorDTO vendorDTO);

    VendorDTO patchVendorByDTO(Long vendorId, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}

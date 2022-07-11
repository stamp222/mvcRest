package com.example.mvcrest.api.v1.mapper;

import com.example.mvcrest.api.v1.model.VendorDTO;
import com.example.mvcrest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor customer);

    Vendor vendorDTOtoVendor(VendorDTO customer);
}

package com.example.mvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class VendorListDTO {
    List<VendorDTO> vendors = new ArrayList<VendorDTO>();
}

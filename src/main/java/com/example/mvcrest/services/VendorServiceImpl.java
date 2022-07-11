package com.example.mvcrest.services;

import com.example.mvcrest.ResourceNotFoundException;
import com.example.mvcrest.api.v1.mapper.VendorMapper;
import com.example.mvcrest.api.v1.model.VendorDTO;
import com.example.mvcrest.api.v1.model.VendorListDTO;
import com.example.mvcrest.controllers.v1.VendorController;
import com.example.mvcrest.domain.Vendor;
import com.example.mvcrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService{


    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }


    @Override
    public VendorListDTO getAllVendors() {
        List<VendorDTO> collect = vendorRepository.findAll().stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());

        return new VendorListDTO(collect) ;
    }
    @Override
    public VendorDTO getVendorById(Long vendorId) throws Exception {
        Optional<Vendor> vendorOptional = vendorRepository.findById(vendorId);
        return vendorOptional.map(vendor -> {
            VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
            vendorDTO.setVendorUrl(getVendorUrl(vendorId));
            return vendorDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOtoVendor(vendorDTO));
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor save = vendorRepository.save(vendor);
        VendorDTO vendorDTO1 = vendorMapper.vendorToVendorDTO(save);
        vendorDTO1.setVendorUrl(getVendorUrl(save.getId()));
        return vendorDTO1;
    }

    @Override
    public VendorDTO saveVendorByDTO(Long vendorId, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendor.setId(vendorId);
        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendorByDTO(Long vendorId, VendorDTO vendorDTO) {
        return vendorRepository.findById(vendorId)
                .map(vendor -> {
                    if (vendorDTO.getName() != null) {
                        vendor.setName(vendorDTO.getName());
                    }
                    VendorDTO vendorDTO1 = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
                    vendorDTO1.setVendorUrl(getVendorUrl(vendorId));
                    return vendorDTO1;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    public String getVendorUrl(Long id) {
        return VendorController.API_V_1_CUSTOMERS + "/" + id;
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }


}

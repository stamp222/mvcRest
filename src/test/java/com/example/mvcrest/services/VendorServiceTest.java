package com.example.mvcrest.services;

import com.example.mvcrest.api.v1.mapper.VendorMapper;
import com.example.mvcrest.api.v1.model.VendorDTO;
import com.example.mvcrest.api.v1.model.VendorListDTO;
import com.example.mvcrest.domain.Vendor;
import com.example.mvcrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VendorServiceTest {

    public static final String NAME_1 = "My Vendor";
    public static final long ID_1 = 1L;
    public static final String NAME_2 = "My Vendor";
    public static final long ID_2 = 2L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @BeforeEach
    void setUp() {

        vendorService = new VendorServiceImpl(vendorMapper, vendorRepository);
    }

    @Test
    void getAllVendors() {
        List<Vendor> vendorList = Arrays.asList(getVendor1(), getVendor2());

        given(vendorRepository.findAll()).willReturn(vendorList);

//        when(vendorRepository.findAll()).thenReturn(vendorList);

        VendorListDTO vendorsDTOs = vendorService.getAllVendors();

        then(vendorRepository).should(times(1)).findAll();

        assertThat(vendorsDTOs.getVendors().size(), is(equalTo(2)));

//        assertEquals(2L, vendorsDTOs.getVendors().size());
    }

    @Test
    void getVendorById() throws Exception {
        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        then(vendorRepository).should(times(1)).findById(anyLong());

        assertThat(vendorDTO.getName(),is(equalTo(NAME_1)));

//        Vendor cus = new Vendor();
//        cus.setId(1L);
//        cus.setName("Apple");
//        Optional<Vendor> vendorOptional = Optional.of(cus);
//
//        when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);
//
//        VendorDTO vendorById = vendorService.getVendorById(1L);
//
//        assertEquals(1L, cus.getId());
//        assertEquals("Apple", vendorById.getName());
    }

    @Test
    void createNewVendorTest() throws Exception {} {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Apple");

        Vendor cus = getVendor1();

//        given(vendorRepository.save(any(Vendor.class))).willReturn(cus);
//
//        VendorDTO newVendor = vendorService.createNewVendor(vendorDTO);
//
//        then(vendorRepository).should().save(any(Vendor.class));
//
//        assertThat(newVendor.getVendorUrl(),containsString("1"));

        //TODO fix
//            when(vendorRepository.save(any(Vendor.class))).thenReturn(cus);
//
//        VendorDTO newVendor = vendorService.createNewVendor(vendorDTO);
//
//        assertEquals(vendorDTO.getFirstName(), newVendor.getFirstName());
//        assertEquals("/api/v1/vendors/1", newVendor.getVendorUrl());
    }

    @Test
    void saveVendorByDTO() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Apple");

        Vendor cus = new Vendor();
        cus.setId(1L);
        cus.setName("Apple");

        //TODO fix
//        when(vendorRepository.save(any(Vendor.class))).thenReturn(cus);
//
//        VendorDTO savedVendor = vendorService.saveVendorByDTO(1L, vendorDTO);
//
//        assertEquals(vendorDTO.getFirstName(), savedVendor.getFirstName());
//        assertEquals("/api/v1/vendors/1", savedVendor.getVendorUrl());
    }

    @Test
    void deleteVendorTest() throws Exception {
        Long id = 1L;
        vendorRepository.deleteById(id);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }

    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_1);
        vendor.setId(ID_1);
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_2);
        vendor.setId(ID_2);
        return vendor;
    }
}
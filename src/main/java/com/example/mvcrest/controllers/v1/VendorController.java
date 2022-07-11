package com.example.mvcrest.controllers.v1;

import com.example.mvcrest.api.v1.model.VendorDTO;
import com.example.mvcrest.api.v1.model.VendorListDTO;
import com.example.mvcrest.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VendorController.API_V_1_CUSTOMERS)
public class VendorController {

    public static final String API_V_1_CUSTOMERS = "/api/v1/vendors";

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    private final VendorService vendorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendorss() {
        return vendorService.getAllVendors();
    }


    @GetMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long vendorId) throws Exception {
        return vendorService.getVendorById(vendorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO)  {
        return vendorService.createNewVendor(vendorDTO);
    }

    @PutMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long vendorId, @RequestBody VendorDTO vendorDTO)  {
        return vendorService.saveVendorByDTO(vendorId, vendorDTO);
    }

    @PatchMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long vendorId, @RequestBody VendorDTO vendorDTO)  {
        return vendorService.patchVendorByDTO(vendorId, vendorDTO);
    }

    @DeleteMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long vendorId) {
        vendorService.deleteVendorById(vendorId);
    }

}

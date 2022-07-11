package com.example.mvcrest.bootstrap;

import com.example.mvcrest.domain.Category;
import com.example.mvcrest.domain.Customer;
import com.example.mvcrest.domain.Vendor;
import com.example.mvcrest.repositories.CategoryRepository;
import com.example.mvcrest.repositories.CustomerRepository;
import com.example.mvcrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadVendors() {
        Vendor ven1 = new Vendor();
        ven1.setName("Apple");
        Vendor ven2 = new Vendor();
        ven2.setName("Microsoft");
        Vendor ven3 = new Vendor();
        ven3.setName("Google");
        vendorRepository.save(ven1);
        vendorRepository.save(ven2);
        vendorRepository.save(ven3);
        System.out.println("----------Vendors Loaded: "+ vendorRepository.count() + "----------" );
    }

    private void loadCustomers() {
        Customer cus1 = new Customer();
        cus1.setFirstName("Jacek");
        cus1.setLastName("Szyper");
        Customer cus2 = new Customer();
        cus2.setFirstName("Magda");
        cus2.setLastName("Ptasik");
        Customer cus3 = new Customer();
        cus3.setFirstName("Adam");
        cus3.setLastName("Pawera");
        customerRepository.save(cus1);
        customerRepository.save(cus2);
        customerRepository.save(cus3);
        System.out.println("----------Data customers Loaded: "+ customerRepository.count() + "----------" );
    }

    private void loadCategories() {
        Category cat1 = new Category();
        cat1.setName("Fruits");
        Category cat2 = new Category();
        cat2.setName("Dried");
        Category cat3 = new Category();
        cat3.setName("Fresh");
        Category cat4 = new Category();
        cat4.setName("Exotic");
        Category cat5 = new Category();
        cat5.setName("Nuts");
        categoryRepository.save(cat1);
        categoryRepository.save(cat2);
        categoryRepository.save(cat3);
        categoryRepository.save(cat4);
        categoryRepository.save(cat5);
        System.out.println("----------Data categories Loaded: "+ categoryRepository.count() + "----------" );
    }
}

package com.example.mvcrest.bootstrap;

import com.example.mvcrest.domain.Category;
import com.example.mvcrest.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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
        System.out.println("----------Data Loaded: "+ categoryRepository.count() + "----------" );
    }
}

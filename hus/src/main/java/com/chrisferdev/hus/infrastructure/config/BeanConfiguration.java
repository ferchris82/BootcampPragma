package com.chrisferdev.hus.infrastructure.config;

import com.chrisferdev.hus.application.BrandService;
import com.chrisferdev.hus.application.CategoryService;
import com.chrisferdev.hus.domain.port.IBrandRepository;
import com.chrisferdev.hus.domain.port.ICategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Clase de configuración de Spring que se encarga de definir beans para la aplicación.
@Configuration
public class BeanConfiguration {

    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository){
        return new CategoryService(iCategoryRepository);
    }

    @Bean
    public BrandService brandService(IBrandRepository iBrandRepository){
        return new BrandService(iBrandRepository);
    }
}

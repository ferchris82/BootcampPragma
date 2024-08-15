package com.chrisferdev.hus.infrastructure.config;

import com.chrisferdev.hus.application.CategoryService;
import com.chrisferdev.hus.domain.port.ICategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository){
        return new CategoryService(iCategoryRepository);
    }
}

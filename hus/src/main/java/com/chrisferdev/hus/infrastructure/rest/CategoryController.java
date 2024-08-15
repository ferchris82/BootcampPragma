package com.chrisferdev.hus.infrastructure.rest;

import com.chrisferdev.hus.application.CategoryService;
import com.chrisferdev.hus.domain.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// Se expone como una apiRest
@RequestMapping("api/v1/admin/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }
}

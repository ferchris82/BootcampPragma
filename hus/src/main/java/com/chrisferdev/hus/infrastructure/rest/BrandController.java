package com.chrisferdev.hus.infrastructure.rest;

import com.chrisferdev.hus.application.BrandService;
import com.chrisferdev.hus.domain.model.Brand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseEntity<Brand>save(@RequestBody Brand brand){
        return new ResponseEntity<>(brandService.saveBrand(brand), HttpStatus.CREATED);
    }
}

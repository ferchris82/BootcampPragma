package com.chrisferdev.hus.infrastructure.rest;

import com.chrisferdev.hus.application.BrandService;
import com.chrisferdev.hus.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping
    public ResponseEntity<Page<Brand>> getBrands(
           @RequestParam(defaultValue = "asc") String sortOrder,
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "3") int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<Brand> brands = brandService.findAllBrands(sortOrder, pageable);
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

}

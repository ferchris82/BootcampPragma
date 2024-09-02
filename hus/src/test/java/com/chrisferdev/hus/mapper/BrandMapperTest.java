package com.chrisferdev.hus.mapper;

import com.chrisferdev.hus.domain.model.Brand;
import com.chrisferdev.hus.ports.driven.jpa.entity.BrandEntity;
import com.chrisferdev.hus.ports.driven.jpa.mapper.BrandMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class BrandMapperTest {

    private final BrandMapper brandMapper = Mappers.getMapper(BrandMapper.class);

    @Test
    void toBrand_successful() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1L);
        brandEntity.setName("Brand Name");
        brandEntity.setDescription("Brand Description");

        Brand brand = brandMapper.toBrand(brandEntity);

        assertEquals(1L, brand.getId());
        assertEquals("Brand Name", brand.getName());
        assertEquals("Brand Description", brand.getDescription());
    }

    @Test
    void toBrandEntity_successful() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Brand Name");
        brand.setDescription("Brand Description");

        BrandEntity brandEntity = brandMapper.toBrandEntity(brand);

        assertEquals(1L, brandEntity.getId());
        assertEquals("Brand Name", brandEntity.getName());
        assertEquals("Brand Description", brandEntity.getDescription());
    }

    @Test
    void toBrandList_successful() {
        BrandEntity brandEntity1 = new BrandEntity();
        brandEntity1.setId(1L);
        brandEntity1.setName("Brand 1");
        brandEntity1.setDescription("Description 1");

        BrandEntity brandEntity2 = new BrandEntity();
        brandEntity2.setId(2L);
        brandEntity2.setName("Brand 2");
        brandEntity2.setDescription("Description 2");

        Iterable<BrandEntity> brandEntities = List.of(brandEntity1, brandEntity2);

        Iterable<Brand> brands = brandMapper.toBrandList(brandEntities);

        assertEquals(2, ((List<Brand>) brands).size());
        assertEquals("Brand 1", ((List<Brand>) brands).get(0).getName());
        assertEquals("Brand 2", ((List<Brand>) brands).get(1).getName());
    }
}

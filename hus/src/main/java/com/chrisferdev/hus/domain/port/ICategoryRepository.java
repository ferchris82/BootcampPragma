package com.chrisferdev.hus.domain.port;


//Definir todos los métodos que nos van a permitir trabajar con la entidad categoría

import com.chrisferdev.hus.domain.model.Category;

public interface ICategoryRepository {
    Category save (Category category);
    boolean existsByName(String name);
}

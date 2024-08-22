// Contiene la interfaz que  definen los puntos de entrada y salida del dominio

package com.chrisferdev.hus.domain.port;


import com.chrisferdev.hus.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// Puerto que describe las operaciones que el dominio espera que estén
// disponibles para interactuar con la persistencia de datos

public interface ICategoryRepository {
    Category saveCategory (Category category);
    boolean existsByName(String name);
    Page<Category> findAllCategories(String sortOrder, Pageable pageable);

}

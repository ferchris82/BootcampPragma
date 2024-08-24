/* CategoryService actúa como el intermediario entre el dominio y la capa de persistencia
 (o cualquier otro adaptador).
 */


package com.chrisferdev.hus.application;

import com.chrisferdev.hus.domain.model.Category;
import com.chrisferdev.hus.domain.port.ICategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// Esta clase encapsula la lógica de negocio relacionada con las categorías.
public class CategoryService {
    private final ICategoryRepository iCategoryRepository;
    
    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    public Category saveCategory(Category category){
        return iCategoryRepository.saveCategory(category);
    }


    public Page<Category> findAllCategories(String sortOrder, Pageable pageable) {
        return iCategoryRepository.findAllCategories(sortOrder, pageable);
    }


}

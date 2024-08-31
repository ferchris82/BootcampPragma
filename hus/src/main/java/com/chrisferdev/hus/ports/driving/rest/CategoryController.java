package com.chrisferdev.pragmabackend.ports.driving.rest;

import com.chrisferdev.pragmabackend.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.pragmabackend.domain.api.usecase.CategoryServiceImpl;
import com.chrisferdev.pragmabackend.domain.model.Category;
import com.chrisferdev.pragmabackend.domain.model.PaginatedResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @Operation(
            summary = "Guardar una nueva categoría",
            description = "Guarda una nueva categoría en el sistema. Si ya existe una categoría con el mismo nombre, lanza una excepción.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría guardada con éxito",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))),
            @ApiResponse(responseCode = "409", description = "Ya existe una categoría con ese nombre",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category){
        return new ResponseEntity<>(categoryServiceImpl.saveCategory(category), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Obtener categorías",
            description = "Obtiene una lista de categorías paginadas y ordenadas. Se puede especificar el orden de clasificación, la página y el tamaño de la página.",
            parameters = {
                    @Parameter(name = "sortOrder", description = "Orden de clasificación. Puede ser 'asc' para ascendente o 'desc' para descendente.", example = "asc"),
                    @Parameter(name = "page", description = "Número de la página para la paginación.", example = "0"),
                    @Parameter(name = "size", description = "Número de elementos por página.", example = "10")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categorías recuperadas con éxito.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResult.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida, por ejemplo, parámetros fuera de rango.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping
    public ResponseEntity<PaginatedResult<Category>> getCategories(
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PaginatedResult<Category> result = categoryServiceImpl.findAllCategories(sortOrder, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
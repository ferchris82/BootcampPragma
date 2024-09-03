package com.chrisferdev.hus.infrastructure.driving.rest;

import com.chrisferdev.hus.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.hus.domain.api.usecase.ProductServiceImpl;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.model.Product;
import com.chrisferdev.hus.infrastructure.driving.dto.response.ProductResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @Operation(
            summary = "Guardar un nuevo producto",
            description = "Guarda un nuevo producto en el sistema. Si el producto no tiene una categoría asociada o tiene más de 3, lanza una excepción.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto guardado con éxito",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400", description = "El producto no debe tener una categoría duplicada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productServiceImpl.saveProduct(product), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Obtener productos",
            description = "Obtiene una lista de productos paginadas y ordenadas. Se puede especificar el orden de clasificación, la página y el tamaño de la página.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Productos recuperados con éxito.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResult.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida, por ejemplo, parámetros fuera de rango.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping
    public ResponseEntity<PaginatedResult<ProductResponseDTO>> getProducts(
            @RequestParam(defaultValue = "asc") @Parameter(description = "Orden de clasificación. Puede ser 'asc' para ascendente o 'desc' para descendente.", example = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") @Parameter(description = "Número de la página para la paginación.", example = "0") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Número de elementos por página.", example = "10") int size) {
        PaginatedResult<ProductResponseDTO> result = productServiceImpl.findAllProducts(sortOrder, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(
            summary = "Obtener productos por nombre",
            description = "Obtiene una lista de productos paginadas y ordenadas según el nombre del producto.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Productos recuperados con éxito.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResult.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida, por ejemplo, parámetros fuera de rango.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/by-name")
    public ResponseEntity<PaginatedResult<Product>> getProductsByName(
            @RequestParam @Parameter(description = "Nombre del producto para filtrar.", example = "example") String name,
            @RequestParam(defaultValue = "asc") @Parameter(description = "Orden de clasificación. Puede ser 'asc' para ascendente o 'desc' para descendente.", example = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") @Parameter(description = "Número de la página para la paginación.", example = "0") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Número de elementos por página.", example = "10") int size) {
        PaginatedResult<Product> result = productServiceImpl.findProductsByName(name, sortOrder, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(
            summary = "Obtener productos por marca",
            description = "Obtiene una lista de productos paginadas y ordenadas según la marca del producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos recuperados con éxito.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResult.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida, por ejemplo, parámetros fuera de rango.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/by-brand")
    public ResponseEntity<PaginatedResult<Product>> getProductsByBrand(
            @RequestParam @Parameter(description = "ID de la marca para filtrar.", example = "1") Long brandId,
            @RequestParam(defaultValue = "asc") @Parameter(description = "Orden de clasificación. Puede ser 'asc' para ascendente o 'desc' para descendente.", example = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") @Parameter(description = "Número de la página para la paginación.", example = "0") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Número de elementos por página.", example = "10") int size) {
        PaginatedResult<Product> result = productServiceImpl.findProductsByBrand(brandId, sortOrder, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(
            summary = "Obtener productos por categoría",
            description = "Obtiene una lista de productos paginadas y ordenadas según las categorías asociadas al producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos recuperados con éxito.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResult.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida, por ejemplo, parámetros fuera de rango.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/by-category")
    public ResponseEntity<PaginatedResult<Product>> getProductsByCategory(
            @RequestParam @Parameter(description = "Lista de IDs de categorías para filtrar. Ejemplo: 1,2,3", example = "1,2,3") List<Long> categoryIds,
            @RequestParam(defaultValue = "asc") @Parameter(description = "Orden de clasificación. Puede ser 'asc' para ascendente o 'desc' para descendente.", example = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") @Parameter(description = "Número de la página para la paginación.", example = "0") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Número de elementos por página.", example = "10") int size,
            @RequestParam(defaultValue = "name") @Parameter(description = "Campo por el cual ordenar los resultados. Puede ser 'name', 'brand', o 'category'.", example = "name") String sortBy) {
        PaginatedResult<Product> result = productServiceImpl.findProductsByCategory(categoryIds, sortOrder, page, size, sortBy);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

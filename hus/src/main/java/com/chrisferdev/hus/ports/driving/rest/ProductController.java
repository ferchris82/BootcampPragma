package com.chrisferdev.pragmabackend.ports.driving.rest;

import com.chrisferdev.pragmabackend.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.pragmabackend.domain.api.usecase.ProductServiceImpl;
import com.chrisferdev.pragmabackend.domain.model.PaginatedResult;
import com.chrisferdev.pragmabackend.domain.model.Product;
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
@RequestMapping("api/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @Operation(
            summary = "Guardar un nuevo producto",
            description = "Guarda un nuevo producto en el sistema. Si el producto no tiene una categoría asociada o tiene más de 3 , lanza una excepción.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto guardado con éxito",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400", description = "El producto no debe tener una categoría duplicada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productServiceImpl.saveProduct(product), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Obtener productos",
            description = "Obtiene una lista de productos paginadas y ordenadas. Se puede especificar el orden de clasificación, la página y el tamaño de la página.",
            parameters = {
                    @Parameter(name = "sortOrder", description = "Orden de clasificación. Puede ser 'asc' para ascendente o 'desc' para descendente.", example = "asc"),
                    @Parameter(name = "page", description = "Número de la página para la paginación.", example = "0"),
                    @Parameter(name = "size", description = "Número de elementos por página.", example = "10")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Productos recuperadas con éxito.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResult.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida, por ejemplo, parámetros fuera de rango.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping
    public ResponseEntity<PaginatedResult<Product>> getProducts(
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PaginatedResult<Product> result = productServiceImpl.findAllProducts(sortOrder, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @Operation(
            summary = "Obtener productos por nombre",
            description = "Obtiene una lista de productos paginadas y ordenadas según el nombre del producto.",
            parameters = {
                    @Parameter(name = "name", description = "Nombre del producto para filtrar.", example = "example"),
                    @Parameter(name = "sortOrder", description = "Orden de clasificación. Puede ser 'asc' para ascendente o 'desc' para descendente.", example = "asc"),
                    @Parameter(name = "page", description = "Número de la página para la paginación.", example = "0"),
                    @Parameter(name = "size", description = "Número de elementos por página.", example = "10")
            }
    )
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
            @RequestParam String name,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PaginatedResult<Product> result = productServiceImpl.findProductsByName(name, sortOrder, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}

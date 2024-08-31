package com.chrisferdev.pragmabackend.ports.driving.rest;

import com.chrisferdev.pragmabackend.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.pragmabackend.domain.api.usecase.BrandServiceImpl;
import com.chrisferdev.pragmabackend.domain.model.Brand;
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
@RequestMapping("api/brands")
public class BrandController {
    private final BrandServiceImpl brandServiceImpl;

    public BrandController(BrandServiceImpl brandServiceImpl) {
        this.brandServiceImpl = brandServiceImpl;
    }

    @Operation(
            summary = "Guardar una nueva marca",
            description = "Guarda una nueva marca en el sistema. Si ya existe una marca con el mismo nombre, lanza una excepción.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marca guardada con éxito",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "409", description = "Ya existe una marca con ese nombre",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Brand> save(@RequestBody Brand brand){
        return new ResponseEntity<>(brandServiceImpl.saveBrand(brand), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Obtener marcas",
            description = "Obtiene una lista de marcas paginadas y ordenadas. Se puede especificar el orden de clasificación, la página y el tamaño de la página.",
            parameters = {
                    @Parameter(name = "sortOrder", description = "Orden de clasificación. Puede ser 'asc' para ascendente o 'desc' para descendente.", example = "asc"),
                    @Parameter(name = "page", description = "Número de la página para la paginación.", example = "0"),
                    @Parameter(name = "size", description = "Número de elementos por página.", example = "10")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Marcas recuperadas con éxito.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResult.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida, por ejemplo, parámetros fuera de rango.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping
    public ResponseEntity<PaginatedResult<Brand>> getBrands(
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PaginatedResult<Brand> result = brandServiceImpl.findAllBrands(sortOrder, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

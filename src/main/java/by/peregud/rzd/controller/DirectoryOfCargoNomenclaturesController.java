package by.peregud.rzd.controller;

import by.peregud.rzd.dto.DirectoryOfCargoNomenclaturesDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import by.peregud.rzd.service.DirectoryOfCargoNomenclaturesService;

import java.util.List;

@RestController
@RequestMapping("nomenclatures/")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Product Nomenclature", description = "CRUD operations for DirectoryOfCargoNomenclatures")
public class DirectoryOfCargoNomenclaturesController {

    private final DirectoryOfCargoNomenclaturesService directoryService;

    @GetMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Get product nomenclature by ID")
    public ResponseEntity<DirectoryOfCargoNomenclaturesDto> getNomenclatureById(
            @Parameter(description = "Unique identifier")
            @PathVariable("id") Integer id) {
        log.info("Request to get product by {}: {}", "id", id);
        var result = directoryService.getNomenclatureById(id);
        return result.map(
                        directory -> new ResponseEntity<>(
                                directory, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("code/{code}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Get product nomenclature by code")
    public ResponseEntity<DirectoryOfCargoNomenclaturesDto> getNomenclatureByCode(
            @Parameter(description = "Product code")
            @PathVariable("code") String code) {
        log.info("Request to get product by {}: {}", "code", code);
        var result = directoryService.getNomenclatureByCode(code);
        return result.map(
                        directory -> new ResponseEntity<>(
                                directory, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("shipping_name/{shipping_name}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Get product nomenclature by product name")
    public ResponseEntity<DirectoryOfCargoNomenclaturesDto> getNomenclatureByName(
            @Parameter(description = "Product name")
            @PathVariable("shipping_name") String shippingName) {
        log.info("Request to get product by {}: {}", "shippingName", shippingName);
        var result = directoryService.getNomenclatureByName(shippingName);
        return result.map(
                        directory -> new ResponseEntity<>(
                                directory, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("all")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Get list of all product nomenclatures")
    public ResponseEntity<List<DirectoryOfCargoNomenclaturesDto>> getAllNomenclatures() {
        log.info("Request to get all items");
        var result = directoryService.getAllNomenclatures();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("add")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Add a new product nomenclature")
    public ResponseEntity<Void> addNomenclature(@RequestBody DirectoryOfCargoNomenclaturesDto directory) {
        log.info("Request to add product: {}", directory.getShippingName());
        if (directoryService.addNomenclature(directory))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Update product nomenclature by ID")
    public ResponseEntity<Void> updateNomenclatureById(
            @Parameter(description = "Unique identifier")
            @PathVariable("id") Integer id,
            @RequestBody DirectoryOfCargoNomenclaturesDto directoryDto) {
        log.info("Request to update product by {}: {}", "id", id);
        if (directoryService.updateNomenclatureById(id, directoryDto))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Delete product nomenclature by ID")
    @DeleteMapping("id/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteNomenclatureById(
            @Parameter(description = "Unique identifier")
            @PathVariable("id") Integer id) {
        log.info("Request to delete product by {}: {}", "id", id);
        if (directoryService.deleteNomenclatureById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}

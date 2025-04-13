package by.peregud.rzd.controller;

import by.peregud.rzd.dto.scale.ScaleCreateDto;
import by.peregud.rzd.dto.scale.ScaleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import by.peregud.rzd.service.ScaleService;

import java.util.List;

@RestController
@RequestMapping("scale/")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "List of Wagons with Attributes", description = "CRUD operations for Scale")
public class ScaleController {

    private final ScaleService scaleService;

    @Operation(summary = "Get all Wagons")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @GetMapping("all")
    public ResponseEntity<List<ScaleDto>> getAll() {
        var result = scaleService.getAllScale();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Get a wagon with attributes by ID")
    public ResponseEntity<ScaleDto> getScaleById(
            @Parameter(description = "Unique identifier")
            @PathVariable("id") Integer id) {
        var result = scaleService.getScaleById(id);
        return result.map(
                        scaleDto -> new ResponseEntity<>(
                                scaleDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("add")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Add a new wagon with attributes")
    public ResponseEntity<Void> addScale(@RequestBody ScaleCreateDto scaleCreateDto) {
        log.info("Request to add new wagon with attributes: {}", scaleCreateDto.getWagonPassportId());
        if (scaleService.addScale(scaleCreateDto))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Update wagon attributes by ID")
    public ResponseEntity<Void> updateScale(
            @Parameter(description = "Unique identifier")
            @PathVariable("id") Integer id,
            @RequestBody ScaleCreateDto updateScaleDto) {
        log.info("Request to update wagon attributes by {}: {}", "id", id);
        if (scaleService.updateScaleById(id, updateScaleDto))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Delete wagon attributes by ID")
    @DeleteMapping("id/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteScaleById(
            @Parameter(description = "Unique identifier")
            @PathVariable("id") Integer id) {
        log.info("Request to delete wagon with attributes by {}: {}", "id", id);
        if (scaleService.deleteScaleById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}

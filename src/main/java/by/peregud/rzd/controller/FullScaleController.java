package by.peregud.rzd.controller;

import by.peregud.rzd.dto.fullscale.FullScaleCreateDto;
import by.peregud.rzd.dto.fullscale.FullScaleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import by.peregud.rzd.service.FullScaleService;

import java.util.List;

@RestController
@RequestMapping("full_scale/")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "List of Train Compositions", description = "CRUD operations for FullScale")
public class FullScaleController {

    private final FullScaleService fullScaleService;

    @Operation(summary = "Get all train compositions")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @GetMapping("all")
    public ResponseEntity<List<FullScaleDto>> getAll() {
        log.info("Request to get all trains");
        var result = fullScaleService.getAllFullScale();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Get a train with its composition by ID")
    public ResponseEntity<FullScaleDto> getFullScaleById(
            @Parameter(description = "Unique identifier")
            @PathVariable("id") Integer id) {
        log.info("Request to get train composition by {}={}", "id", id);
        var result = fullScaleService.getFullScaleById(id);
        return result.map(
                        fullScaleDto -> new ResponseEntity<>(
                                fullScaleDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("add")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Add a new train with its composition")
    public ResponseEntity<Void> addFullScale(@RequestBody FullScaleCreateDto fullScaleDto) {
        log.info("Request to add new train: {}", fullScaleDto.getCompositionNumber());
        if (fullScaleService.addFullScale(fullScaleDto))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Update a train with its composition by ID")
    public ResponseEntity<Void> updateFullScaleById(
            @Parameter(description = "Unique identifier")
            @PathVariable("id") Integer id,
            @RequestBody FullScaleCreateDto fullScaleCreateDto) {
        log.info("Request to update train with composition by {}: {}", "id", id);
        if (fullScaleService.updateFullScaleById(id, fullScaleCreateDto))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Delete a train with its composition by ID")
    @DeleteMapping("id/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteFullScaleById(
            @Parameter(description = "Unique identifier")
            @PathVariable("id") Integer id) {
        log.info("Request to delete train with composition by {}: {}", "id", id);
        if (fullScaleService.deleteFullScaleById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}

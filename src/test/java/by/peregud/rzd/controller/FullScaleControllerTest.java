package by.peregud.rzd.controller;

import by.peregud.rzd.dto.DirectoryOfCargoNomenclaturesDto;
import by.peregud.rzd.dto.WagonPassportDto;
import by.peregud.rzd.dto.fullscale.FullScaleCreateDto;
import by.peregud.rzd.dto.fullscale.FullScaleDto;
import by.peregud.rzd.dto.scale.ScaleDto;
import by.peregud.rzd.enums.WagonType;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import by.peregud.rzd.service.FullScaleService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FullScaleControllerTest {
    @InjectMocks
    FullScaleController fullScaleController;

    @Mock
    FullScaleService fullScaleService;

    FullScaleDto fullScaleDto;
    FullScaleCreateDto createDto;

    private final int ID = 1;
    private final int NUMBER_FULL = 54785;
    private final int NUMBER_SCALE = 1;
    private final int NUMBER_WAGON = 14551;
    List<ScaleDto> scaleDtoList = new ArrayList<>(List.of(
            ScaleDto.builder()
                    .cargoWeight(BigDecimal.valueOf(5544))
                    .nomenclatures(List.of(DirectoryOfCargoNomenclaturesDto.builder()
                            .shippingName("test")
                            .code("5458-5478")
                            .build()))
                    .wagonPassport(WagonPassportDto.builder()
                            .number(NUMBER_WAGON)
                            .loadCapacity(WagonType.COVERED1.getLoadCapacity())
                            .tareWeight(WagonType.COVERED1.getTareWeight())
                            .type(WagonType.COVERED1)
                            .build())
                    .wagonWeight(BigDecimal.valueOf(WagonType.COVERED1.getTareWeight()))
                    .serialNumber(NUMBER_SCALE)
                    .build()));


    @BeforeEach
    void setUp() {
        fullScaleDto = FullScaleDto.builder()
                .compositionNumber(NUMBER_FULL)
                .scales(scaleDtoList)
                .build();
        createDto = FullScaleCreateDto.builder()
                .scales(List.of(1, 2, 3))
                .compositionNumber(NUMBER_FULL)
                .build();
        System.out.println(Strings.repeat("-", 100));
        System.out.println("test started");
    }

    @AfterEach
    void tearDown() {
        fullScaleDto = null;
        createDto = null;
        System.out.println("test finished");
        System.out.println(Strings.repeat("-", 100));
    }


    @Test
    void getAll() {
        when(fullScaleService.getAllFullScale())
                .thenReturn(List.of(fullScaleDto));
        var result = fullScaleController.getAll();
        assertEquals(
                new ResponseEntity<>(List.of(fullScaleDto), HttpStatus.OK),
                result);
    }

    @Test
    void getFullScaleByIdOk() {
        when(fullScaleService.getFullScaleById(any()))
                .thenReturn(Optional.of(fullScaleDto));

        var result = fullScaleController.getFullScaleById(ID);
        assertEquals(new ResponseEntity<>(fullScaleDto, HttpStatus.OK), result);
    }

    @Test
    void getFullScaleByIBad() {
        when(fullScaleService.getFullScaleById(any()))
                .thenReturn(Optional.empty());

        var result = fullScaleController.getFullScaleById(ID);
        assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), result);
    }

    @Test
    void addFullScaleOk() {
        when(fullScaleService.addFullScale(any()))
                .thenReturn(true);

        var result = fullScaleController.addFullScale(createDto);
        assertEquals(new ResponseEntity<>(HttpStatus.CREATED), result);
    }

    @Test
    void addFullScaleBad() {
        when(fullScaleService.addFullScale(any()))
                .thenReturn(false);

        var result = fullScaleController.addFullScale(createDto);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), result);
    }

    @Test
    void updateFullScaleByIdOk() {
        when(fullScaleService.updateFullScaleById(any(), any()))
                .thenReturn(true);

        var result = fullScaleController.updateFullScaleById(ID, createDto);
        assertEquals(new ResponseEntity<>(HttpStatus.ACCEPTED), result);
    }

    @Test
    void updateFullScaleByIdBad() {
        when(fullScaleService.updateFullScaleById(any(), any()))
                .thenReturn(false);

        var result = fullScaleController.updateFullScaleById(ID, createDto);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED), result);
    }

    @Test
    void deleteFullScaleByIdOk() {
        when(fullScaleService.deleteFullScaleById(any()))
                .thenReturn(true);

        var result = fullScaleController.deleteFullScaleById(ID);
        assertEquals(new ResponseEntity<>(HttpStatus.OK), result);
    }
    @Test
    void deleteFullScaleByIdBad() {
        when(fullScaleService.deleteFullScaleById(any()))
                .thenReturn(false);

        var result = fullScaleController.deleteFullScaleById(ID);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED), result);
    }
}
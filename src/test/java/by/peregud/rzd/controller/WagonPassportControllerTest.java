package by.peregud.rzd.controller;

import by.peregud.rzd.dto.WagonPassportDto;
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
import by.peregud.rzd.service.WagonPassportService;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WagonPassportControllerTest {

    @Mock
    WagonPassportService wagonPassportService;

    @InjectMocks
    WagonPassportController wagonPassportController;
    private final int ID = 1;
    private final int NUMBER = 1111;
    WagonPassportDto wagonPassportDto;



    @BeforeEach
    void setUp() {
        System.out.println(Strings.repeat("-", 100));
        System.out.println("test start");
        wagonPassportDto = WagonPassportDto.builder()
                .number(NUMBER)
                .type(WagonType.COVERED1)
                .build();
    }

    @AfterEach
    void tearDown() {
        wagonPassportDto = null;
        System.out.println("test finished");
        System.out.println(Strings.repeat("-", 100));
    }

    @Test
    void getWagonPassportOk() {
        when(wagonPassportService.getWagonPassport(ID))
                .thenReturn(Optional.of(wagonPassportDto));
        var result = wagonPassportController.getWagonPassport(ID);
        assertEquals(new ResponseEntity<>(wagonPassportDto,HttpStatus.OK), result);
    }
    @Test
    void getWagonPassportBad() {
        when(wagonPassportService.getWagonPassport(ID))
                .thenReturn(Optional.empty());
        var result = wagonPassportController.getWagonPassport(ID);
        assertEquals( new ResponseEntity<>(HttpStatus.BAD_REQUEST), result);
    }


    @Test
    void getWagonPassportByNumberOk() {
        when(wagonPassportService.getWagonPassportByNumber(NUMBER))
                .thenReturn(Optional.of(wagonPassportDto));
        var result = wagonPassportController.getWagonPassportByNumber(NUMBER);
        assertEquals(new ResponseEntity<>(wagonPassportDto,HttpStatus.OK), result);
    }

    @Test
    void getWagonPassportByNumberBad() {
        when(wagonPassportService.getWagonPassportByNumber(NUMBER))
                .thenReturn(Optional.empty());
        var result = wagonPassportController.getWagonPassportByNumber(NUMBER);
        assertEquals( new ResponseEntity<>(HttpStatus.BAD_REQUEST), result);
    }

    @Test
    void getAllWagonPassportOk() {
        when(wagonPassportService.getAllWagonPassport())
                .thenReturn(List.of(wagonPassportDto));
        var result = wagonPassportController.getAllWagonPassport();
        assertEquals(new ResponseEntity<>(List.of(wagonPassportDto),HttpStatus.OK), result);
    }

    @Test
    void addWagonPassportOk() {
        when(wagonPassportService.addWagonPassport(wagonPassportDto))
                .thenReturn(true);
        var result = wagonPassportController.addWagonPassport(wagonPassportDto);
        assertEquals(new ResponseEntity<>(HttpStatus.CREATED), result);
    }

    @Test
    void addWagonPassportBad() {
        when(wagonPassportService.addWagonPassport(wagonPassportDto))
                .thenReturn(false);
        var result = wagonPassportController.addWagonPassport(wagonPassportDto);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), result);
    }

    @Test
    void updateWagonPassportOk() {
        when(wagonPassportService.updateWagonPassport(ID, wagonPassportDto))
                .thenReturn(true);
        var result = wagonPassportController.updateWagonPassport(ID, wagonPassportDto);
        assertEquals(new ResponseEntity<>(HttpStatus.ACCEPTED), result);
    }
    @Test
    void updateWagonPassportBad() {
        when(wagonPassportService.updateWagonPassport(ID, wagonPassportDto))
                .thenReturn(false);
        var result = wagonPassportController.updateWagonPassport(ID, wagonPassportDto);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED), result);
    }

    @Test
    void deleteWagonPassportOk() {
        when(wagonPassportService.deleteWagonPassport(ID))
                .thenReturn(true);
        var result = wagonPassportController.deleteWagonPassport(ID);
        assertEquals(new ResponseEntity<>(HttpStatus.OK), result);
    }

    @Test
    void deleteWagonPassportBad() {
        when(wagonPassportService.deleteWagonPassport(ID))
                .thenReturn(false);
        var result = wagonPassportController.deleteWagonPassport(ID);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED), result);
    }
}
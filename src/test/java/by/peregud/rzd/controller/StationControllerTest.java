package by.peregud.rzd.controller;

import by.peregud.rzd.dto.StationDto;
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
import by.peregud.rzd.service.StationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StationControllerTest {

    @Mock
    StationService stationService;

    @InjectMocks
    StationController stationController;
    StationDto stationDto;
    private final int ID = 1;
    private final String STATION_NAME = "Train station";
    private final List<Integer> integerList = new ArrayList<>(
            List.of(
                    1,
                    2,
                    3,
                    4,
                    5)
    );


    @BeforeEach
    void setUp() {
        stationDto = StationDto.builder()
                .stationName(STATION_NAME)
                .number(StationDto.getListOfNumber(integerList))
                .build();
        System.out.println(Strings.repeat("-", 100));
        System.out.println("test started");

    }

    @AfterEach
    void tearDown() {
        stationDto = null;
        System.out.println("test finished");
        System.out.println(Strings.repeat("-", 100));
    }

    @Test
    void getStationByIdOk() {
        when(stationService.getStationById(ID))
                .thenReturn(Optional.of(stationDto));

        var result = stationController.getStationById(ID);
        assertEquals(new ResponseEntity<>(stationDto, HttpStatus.OK), result);
    }

    @Test
    void getStationByIdBad() {
        when(stationService.getStationById(ID))
                .thenReturn(Optional.empty());

        var result = stationController.getStationById(ID);
        assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), result);
    }

    @Test
    void getAllStationsOk() {
        when(stationService.getAllStations())
                .thenReturn(List.of(stationDto));

        var result = stationController.getAllStations();
        assertEquals(new ResponseEntity<>(List.of(stationDto), HttpStatus.OK), result);
    }

    @Test
    void addStationOk() {
        when(stationService.addStation(stationDto))
                .thenReturn(true);

        var result = stationController.addStation(stationDto);
        assertEquals(new ResponseEntity<>(HttpStatus.CREATED), result);
    }

    @Test
    void addStationBad() {
        when(stationService.addStation(stationDto))
                .thenReturn(false);

        var result = stationController.addStation(stationDto);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), result);
    }

    @Test
    void updateStationOk() {
        when(stationService.updateStation(ID, stationDto))
                .thenReturn(true);

        var result = stationController.updateStation(ID, stationDto);
        assertEquals(new ResponseEntity<>(HttpStatus.ACCEPTED), result);
    }

    @Test
    void updateStationBad() {
        when(stationService.updateStation(ID, stationDto))
                .thenReturn(false);

        var result = stationController.updateStation(ID, stationDto);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED), result);
    }

    @Test
    void deleteStationOk() {
        when(stationService.deleteStation(ID))
                .thenReturn(true);

        var result = stationController.deleteStation(ID);
        assertEquals(new ResponseEntity<>(HttpStatus.OK), result);
    }

    @Test
    void deleteStationBad() {
        when(stationService.deleteStation(ID))
                .thenReturn(false);

        var result = stationController.deleteStation(ID);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED), result);
    }
}
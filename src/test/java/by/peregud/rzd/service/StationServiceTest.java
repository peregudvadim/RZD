package by.peregud.rzd.service;

import by.peregud.rzd.repository.StationRepository;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import by.peregud.rzd.dto.StationDto;
import by.peregud.rzd.entity.StationEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StationServiceTest {

    @InjectMocks
    StationService stationService;

    @Mock
    StationRepository stationRepository;

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

    StationEntity stationEntity;


    @BeforeEach
    void setUp() {
        stationDto = StationDto.builder()
                .stationName(STATION_NAME)
                .number(StationDto.getListOfNumber(integerList))
                .build();
        System.out.println(Strings.repeat("-", 100));
        System.out.println("test started");

        stationEntity = StationEntity.addStation(stationDto);

    }

    @AfterEach
    void tearDown() {
        System.out.println("test finished");
        System.out.println(Strings.repeat("-", 100));
    }

    @Test
    void getAllStations() {
        when(stationRepository.findAll())
                .thenReturn(List.of(stationEntity));

        var result = stationService.getAllStations();
        assertEquals(List.of(stationDto), result);
    }

    @Test
    void getStationByIdOk() {
        when(stationRepository.existsById(any()))
                .thenReturn(true);
        when(stationRepository.findById(any()))
                .thenReturn(Optional.of(stationEntity));

        var result = stationService.getStationById(ID);
        assertEquals(Optional.of(stationDto), result);
    }

    @Test
    void getStationByIdBad() {
        when(stationRepository.existsById(any()))
                .thenReturn(false);

        var result = stationService.getStationById(ID);
        assertEquals(Optional.empty(), result);
    }
}
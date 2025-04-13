package by.peregud.rzd.service;

import by.peregud.rzd.dto.WagonPassportDto;
import by.peregud.rzd.enums.WagonType;
import by.peregud.rzd.repository.WagonPassportRepository;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import by.peregud.rzd.entity.WagonPassportEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WagonPassportServiceTest {

    @InjectMocks
    WagonPassportService wagonPassportService;

    @Mock
    WagonPassportRepository wagonPassportRepository;

    private final int ID = 1;
    private final int NUMBER = 1111;
    WagonPassportDto wagonPassportDto;
    WagonPassportEntity wagonPassportEntity;

    @BeforeEach
    void setUp() {
        System.out.println(Strings.repeat("-", 100));
        System.out.println("test started");
        wagonPassportDto = WagonPassportDto.builder()
                .number(NUMBER)
                .loadCapacity(WagonType.COVERED1.getLoadCapacity())
                .tareWeight(WagonType.COVERED1.getTareWeight())
                .type(WagonType.COVERED1)
                .build();

        wagonPassportEntity = WagonPassportEntity.addWagonPassport(wagonPassportDto);
    }

    @AfterEach
    void tearDown() {
        wagonPassportDto = null;
        wagonPassportEntity = null;
        System.out.println("test finished");
        System.out.println(Strings.repeat("-", 100));
    }

    @Test
    void getWagonPassportOk() {
        when(wagonPassportRepository.existsById(ID))
                .thenReturn(true);
        when(wagonPassportRepository.findById(ID))
                .thenReturn(Optional.of(wagonPassportEntity));

        var result = wagonPassportService.getWagonPassport(ID);
        Assertions.assertEquals(Optional.of(wagonPassportDto), result);
    }

    @Test
    void getWagonPassportBad() {
        when(wagonPassportRepository.existsById(ID))
                .thenReturn(false);

        var result = wagonPassportService.getWagonPassport(ID);
        Assertions.assertEquals(Optional.empty(), result);
    }

    @Test
    void getWagonPassportByNumberOk() {
        when(wagonPassportRepository.existsByNumber(NUMBER))
                .thenReturn(true);
        when(wagonPassportRepository.findByNumber(NUMBER))
                .thenReturn(Optional.of(wagonPassportEntity));
        var result = wagonPassportService.getWagonPassportByNumber(NUMBER);
        assertEquals(Optional.of(wagonPassportDto), result);
    }

    @Test
    void getWagonPassportByNumberBad() {
        when(wagonPassportRepository.existsByNumber(NUMBER))
                .thenReturn(false);
        var result = wagonPassportService.getWagonPassportByNumber(NUMBER);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void getAllWagonPassport() {
        when(wagonPassportRepository.findAll())
                .thenReturn(List.of(wagonPassportEntity));

        var result = wagonPassportService.getAllWagonPassport();
        assertEquals(List.of(wagonPassportDto), result);
    }
}
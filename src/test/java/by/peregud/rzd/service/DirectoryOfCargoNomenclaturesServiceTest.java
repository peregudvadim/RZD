package by.peregud.rzd.service;

import by.peregud.rzd.dto.DirectoryOfCargoNomenclaturesDto;
import by.peregud.rzd.repository.DirectoryOfCargoNomenclaturesRepository;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import by.peregud.rzd.entity.DirectoryOfCargoNomenclaturesEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DirectoryOfCargoNomenclaturesServiceTest {
    @InjectMocks
    DirectoryOfCargoNomenclaturesService service;

    @Mock
    DirectoryOfCargoNomenclaturesRepository repository;

    DirectoryOfCargoNomenclaturesDto dto;
    DirectoryOfCargoNomenclaturesEntity entity;
    private final int ID = 1;
    private final String CODE = "458-854";
    private final String SHIPPING_NAME = "test project";

    @BeforeEach
    void setUp() {
        dto = DirectoryOfCargoNomenclaturesDto.builder()
                .code(CODE)
                .shippingName(SHIPPING_NAME)
                .build();
        System.out.println(Strings.repeat("-", 100));
        System.out.println("test started");
        entity = DirectoryOfCargoNomenclaturesEntity.addDirectoryOfCargoNomenclatures(dto);
    }

    @AfterEach
    void tearDown() {
        dto = null;
        entity = null;
        System.out.println("test finished");
        System.out.println(Strings.repeat("-", 100));
    }

    @Test
    void getAllNomenclatures() {
        when(repository.findAll())
                .thenReturn(List.of(entity));

        var result = service.getAllNomenclatures();
        assertEquals(List.of(dto), result);
    }

    @Test
    void getNomenclatureByIdOk() {
        when(repository.existsById(any()))
                .thenReturn(true);
        when(repository.findById(any()))
                .thenReturn(Optional.of(entity));

        var result = service.getNomenclatureById(ID);
        assertEquals(Optional.of(dto), result);
    }
    @Test
    void getNomenclatureByIdBad() {
        when(repository.existsById(any()))
                .thenReturn(false);

        var result = service.getNomenclatureById(ID);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void getNomenclatureByNameOk() {
        when(repository.existsByShippingNameIgnoreCase(any()))
                .thenReturn(true);
        when(repository.findByShippingNameIgnoreCase(any()))
                .thenReturn(Optional.of(entity));

        var result = service.getNomenclatureByName(SHIPPING_NAME);
        assertEquals(Optional.of(dto), result);
    }

    @Test
    void getNomenclatureByNameBad() {
        when(repository.existsByShippingNameIgnoreCase(any()))
                .thenReturn(false);

        var result = service.getNomenclatureByName(SHIPPING_NAME);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void getNomenclatureByCodeOk() {
        when(repository.existsByCode(any()))
                .thenReturn(true);
        when(repository.findByCode(any()))
                .thenReturn(Optional.of(entity));

        var result = service.getNomenclatureByCode(CODE);
        assertEquals(Optional.of(dto), result);
    }

    @Test
    void getNomenclatureByCodeBad() {
        when(repository.existsByCode(any()))
                .thenReturn(false);

        var result = service.getNomenclatureByCode(CODE);
        assertEquals(Optional.empty(), result);
    }
}
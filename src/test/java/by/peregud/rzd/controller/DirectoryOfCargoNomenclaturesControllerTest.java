package by.peregud.rzd.controller;

import by.peregud.rzd.dto.DirectoryOfCargoNomenclaturesDto;
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
import by.peregud.rzd.service.DirectoryOfCargoNomenclaturesService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DirectoryOfCargoNomenclaturesControllerTest {

    @Mock
    DirectoryOfCargoNomenclaturesService service;

    @InjectMocks
    DirectoryOfCargoNomenclaturesController controller;

    DirectoryOfCargoNomenclaturesDto directoryDto;

    private final int ID = 1;
    private final String CODE = "458-854";
    private final String SHIPPING_NAME = "test project";

    @BeforeEach
    void setUp() {
        directoryDto = DirectoryOfCargoNomenclaturesDto.builder()
                .code(CODE)
                .shippingName(SHIPPING_NAME)
                .build();
        System.out.println(Strings.repeat("-", 100));
        System.out.println("test started");
    }

    @AfterEach
    void tearDown() {
        directoryDto = null;
        System.out.println("test finished");
        System.out.println(Strings.repeat("-", 100));
    }

    @Test
    void getNomenclatureByIdOk() {
        when(service.getNomenclatureById(any()))
                .thenReturn(Optional.of(directoryDto));

        var result = controller.getNomenclatureById(ID);
        assertEquals(new ResponseEntity<>(directoryDto, HttpStatus.OK), result);
    }

    @Test
    void getNomenclatureByIdBad() {
        when(service.getNomenclatureById(any()))
                .thenReturn(Optional.empty());

        var result = controller.getNomenclatureById(ID);
        assertEquals(new ResponseEntity<>( HttpStatus.BAD_REQUEST), result);
    }

    @Test
    void getNomenclatureByCodeOk() {
        when(service.getNomenclatureByCode(any()))
                .thenReturn(Optional.of(directoryDto));

        var result = controller.getNomenclatureByCode(CODE);
        assertEquals(new ResponseEntity<>(directoryDto, HttpStatus.OK), result);
    }

    @Test
    void getNomenclatureByCodeBad() {
        when(service.getNomenclatureByCode(any()))
                .thenReturn(Optional.empty());

        var result = controller.getNomenclatureByCode(CODE);
        assertEquals(new ResponseEntity<>( HttpStatus.BAD_REQUEST), result);
    }

    @Test
    void getNomenclatureByNameOk() {
        when(service.getNomenclatureByName(any()))
                .thenReturn(Optional.of(directoryDto));

        var result = controller.getNomenclatureByName(SHIPPING_NAME);
        assertEquals(new ResponseEntity<>(directoryDto, HttpStatus.OK), result);
    }

    @Test
    void getNomenclatureByNameBad() {
        when(service.getNomenclatureByName(any()))
                .thenReturn(Optional.empty());

        var result = controller.getNomenclatureByName(SHIPPING_NAME);
        assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), result);
    }

    @Test
    void getAllNomenclatures() {
        when(service.getAllNomenclatures())
                .thenReturn(List.of(directoryDto));

        var result = controller.getAllNomenclatures();
        assertEquals(new ResponseEntity<>(List.of(directoryDto),HttpStatus.OK), result);
    }

    @Test
    void addNomenclatureOk() {
        when(service.addNomenclature(any()))
                .thenReturn(true);

        var result = controller.addNomenclature(directoryDto);
        assertEquals(new ResponseEntity<>(HttpStatus.CREATED), result);
    }

    @Test
    void addNomenclatureBad() {
        when(service.addNomenclature(any()))
                .thenReturn(false);

        var result = controller.addNomenclature(directoryDto);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), result);
    }

    @Test
    void updateNomenclatureByIdOk() {
        when(service.updateNomenclatureById(any(), any()))
                .thenReturn(true);

        var result = controller.updateNomenclatureById(ID, directoryDto);
        assertEquals(new ResponseEntity<>(HttpStatus.ACCEPTED), result);
    }

    @Test
    void updateNomenclatureByIdBad() {
        when(service.updateNomenclatureById(any(), any()))
                .thenReturn(false);

        var result = controller.updateNomenclatureById(ID, directoryDto);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED), result);
    }

    @Test
    void deleteNomenclatureByIdOk() {
        when(service.deleteNomenclatureById(any()))
                .thenReturn(true);

        var result = controller.deleteNomenclatureById(ID);
        assertEquals(new ResponseEntity<>(HttpStatus.OK), result);
    }

    @Test
    void deleteNomenclatureByIdBad() {
        when(service.deleteNomenclatureById(any()))
                .thenReturn(false);

        var result = controller.deleteNomenclatureById(ID);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED), result);
    }
}
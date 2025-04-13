package by.peregud.rzd.service;

import by.peregud.rzd.dto.DirectoryOfCargoNomenclaturesDto;
import by.peregud.rzd.repository.DirectoryOfCargoNomenclaturesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import by.peregud.rzd.entity.DirectoryOfCargoNomenclaturesEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DirectoryOfCargoNomenclaturesService {

    private final DirectoryOfCargoNomenclaturesRepository directoryRepository;

    public List<DirectoryOfCargoNomenclaturesDto> getAllNomenclatures() {
        var list = directoryRepository.findAll();
        log.info("Presence of the nomenclature list in the request: {}", !list.isEmpty());
        return list.stream()
                .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures)
                .collect(Collectors.toList());
    }

    public Optional<DirectoryOfCargoNomenclaturesDto> getNomenclatureById(Integer id) {
        var exist = directoryRepository.existsById(id);
        log.info("Presence of nomenclature by {}={} : {}", "id", id, exist);
        if (exist) {
            return directoryRepository.findById(id)
                    .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures);
        }
        return Optional.empty();
    }

    public Optional<DirectoryOfCargoNomenclaturesDto> getNomenclatureByName(String name) {
        var exist = directoryRepository.existsByShippingNameIgnoreCase(name);
        log.info("Presence of nomenclature by {}={} : {}", "name", name, exist);
        if (exist) {
            return directoryRepository.findByShippingNameIgnoreCase(name)
                    .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures);
        }
        return Optional.empty();
    }

    public Optional<DirectoryOfCargoNomenclaturesDto> getNomenclatureByCode(String code) {
        var exist = directoryRepository.existsByCode(code);
        log.info("Presence of nomenclature by {}={} : {}", "code", code, exist);
        if (exist) {
            return directoryRepository.findByCode(code)
                    .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures);
        }
        return Optional.empty();
    }

    public boolean addNomenclature(DirectoryOfCargoNomenclaturesDto directory) {
        var id = directoryRepository.save(DirectoryOfCargoNomenclaturesEntity
                .addDirectoryOfCargoNomenclatures(directory)).getId();
        log.info("Saving nomenclature under {}={}", "id", id);
        return directoryRepository.existsById(id);
    }

    public boolean updateNomenclatureById(Integer id, DirectoryOfCargoNomenclaturesDto directoryDto) {
        var item = directoryRepository.existsById(id);
        log.info("Updating nomenclature under {}={}", "id", id);
        if (item) {
            var result = directoryRepository.findById(id)
                    .map(
                            existed -> DirectoryOfCargoNomenclaturesEntity
                                    .updateDirectoryOfCargoNomenclatures(existed, directoryDto)
                    );
            result.ifPresent(directoryRepository::save);
        }
        return item;
    }

    public boolean deleteNomenclatureById(Integer id) {
        var exist = directoryRepository.existsById(id);
        log.info("Presence of nomenclature under {}={} for deletion", "id", id);
        if (exist) {
            directoryRepository.deleteById(id);
        }
        return exist;
    }

}

package by.peregud.rzd.service;

import by.peregud.rzd.dto.scale.ScaleCreateDto;
import by.peregud.rzd.dto.scale.ScaleDto;
import by.peregud.rzd.exception.InvalidRzdException;
import by.peregud.rzd.repository.DirectoryOfCargoNomenclaturesRepository;
import by.peregud.rzd.repository.ScaleRepository;
import by.peregud.rzd.repository.WagonPassportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import by.peregud.rzd.entity.DirectoryOfCargoNomenclaturesEntity;
import by.peregud.rzd.entity.ScaleEntity;
import by.peregud.rzd.entity.WagonPassportEntity;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScaleService {

    private final ScaleRepository scaleRepository;
    private final DirectoryOfCargoNomenclaturesRepository directoryRepository;
    private final WagonPassportRepository wagonPassportRepository;

    public List<ScaleDto> getAllScale() {
        var list = scaleRepository.findAll();
        return list.stream()
                .map(ScaleDto::addScale)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean addScale(ScaleCreateDto scaleCreateDto) {
        var wagonId = getWagonPassport(scaleCreateDto);
        var listDirectory = getNomenclaturesEntities(scaleCreateDto);
        var id = scaleRepository.save(ScaleEntity.addScale(scaleCreateDto, wagonId, listDirectory)).getId();
        return scaleRepository.existsById(id);
    }

    public Optional<ScaleDto> getScaleById(Integer id) {
        var exist = scaleRepository.existsById(id);
        if (exist) {
            return scaleRepository.findById(id)
                    .map(ScaleDto::addScale);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean updateScaleById(Integer id, ScaleCreateDto updateScaleDto) {
        var item = scaleRepository.existsById(id);
        if (item) {
            var wagonId = getWagonPassport(updateScaleDto);
            var listDirectory = getNomenclaturesEntities(updateScaleDto);
            var result = scaleRepository.findById(id)
                    .map(existed -> ScaleEntity.updateScaleById(
                            existed,
                            updateScaleDto,
                            wagonId,
                            listDirectory)
                    );
            result.ifPresent(scaleRepository::save);
        }
        return item;
    }

    @Transactional
    public boolean deleteScaleById(Integer id) {
        var exist = scaleRepository.existsById(id);
        if (exist) {
            scaleRepository.deleteById(id);
        }
        return exist;
    }

    private List<DirectoryOfCargoNomenclaturesEntity> getNomenclaturesEntities(ScaleCreateDto scaleCreateDto) {
        var listDirectory = directoryRepository.findAllById(scaleCreateDto.getNomenclatures());
        if (listDirectory.isEmpty()) {
            return Collections.emptyList();
        }
        return listDirectory;
    }

    private WagonPassportEntity getWagonPassport(ScaleCreateDto scaleCreateDto) {
        return wagonPassportRepository.findById((scaleCreateDto.getWagonPassportId()))
                .orElseThrow(() -> new InvalidRzdException("This carriage does not exist."));
    }
}

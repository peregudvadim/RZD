package by.peregud.rzd.service;

import by.peregud.rzd.dto.WagonPassportDto;
import by.peregud.rzd.repository.WagonPassportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import by.peregud.rzd.entity.WagonPassportEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class WagonPassportService {

    private final WagonPassportRepository wagonPassportRepository;

    public Optional<WagonPassportDto> getWagonPassport(Integer id) {
        var exist = wagonPassportRepository.existsById(id);
        if (exist) {
            return wagonPassportRepository.findById(id)
                    .map(WagonPassportDto::addWagonPassportDto);
        }
        return Optional.empty();
    }



    public Optional<WagonPassportDto> getWagonPassportByNumber(int number) {
        var exist = wagonPassportRepository.existsByNumber(number);
        if (exist) {
            return wagonPassportRepository.findByNumber(number)
                    .map(WagonPassportDto::addWagonPassportDto);
        }
        return Optional.empty();
    }

    public boolean addWagonPassport(WagonPassportDto wagonPassportDto) {
        var id = wagonPassportRepository.save(WagonPassportEntity.addWagonPassport(wagonPassportDto)).getId();
        return wagonPassportRepository.existsById(id);
    }

    public List<WagonPassportDto> getAllWagonPassport() {
        var list = wagonPassportRepository.findAll();
        return list.stream()
                .map(WagonPassportDto::addWagonPassportDto)
                .collect(Collectors.toList());
    }

    public boolean updateWagonPassport(Integer id, WagonPassportDto wagonPassportDto) {
        var item = wagonPassportRepository.existsById(id);
        if (item) {
            var result = wagonPassportRepository.findById(id)
                    .map(existed -> WagonPassportEntity.updateWagonPassport(existed, wagonPassportDto));
            wagonPassportRepository.save(result.get());
        }
        return item;
    }

    public boolean deleteWagonPassport(Integer id) {
        var exist = wagonPassportRepository.existsById(id);
        if (exist) {
            wagonPassportRepository.deleteById(id);
        }
        return exist;
    }

}

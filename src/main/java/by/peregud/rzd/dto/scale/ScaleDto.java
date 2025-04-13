package by.peregud.rzd.dto.scale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import by.peregud.rzd.dto.DirectoryOfCargoNomenclaturesDto;
import by.peregud.rzd.dto.WagonPassportDto;
import by.peregud.rzd.entity.DirectoryOfCargoNomenclaturesEntity;
import by.peregud.rzd.entity.ScaleEntity;
import by.peregud.rzd.entity.WagonPassportEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Information about the lists of wagons with attributes")
public class ScaleDto {
    @JsonProperty("serial_number")
    private Integer serialNumber;

    @JsonProperty("cargo_weight")
    private BigDecimal cargoWeight;

    @JsonProperty("wagon_weight")
    private BigDecimal wagonWeight;
    @JsonProperty("wagon_number")
    private WagonPassportDto wagonPassport;
    private List<DirectoryOfCargoNomenclaturesDto> nomenclatures;

    public static ScaleDto addScale(ScaleEntity scale) {
                return ScaleDto.builder()
                .serialNumber(scale.getSerialNumber())
                .wagonPassport(getWagonPassportDto(scale.getWagonPassport()))
                .nomenclatures(getNomenclaturesDto(scale.getNomenclatures()))
                .cargoWeight(scale.getCargoWeight())
                .wagonWeight(scale.getWagonWeight())
                .build();

    }
    public static WagonPassportDto getWagonPassportDto(WagonPassportEntity wagonPassportEntity) {
        return WagonPassportDto.addWagonPassportDto(wagonPassportEntity);
    }

    public static List<DirectoryOfCargoNomenclaturesDto> getNomenclaturesDto(
            List<DirectoryOfCargoNomenclaturesEntity> directoryList
    ) {
        List<DirectoryOfCargoNomenclaturesDto> dtoList = new ArrayList<>();
        for (DirectoryOfCargoNomenclaturesEntity directoryEntity : directoryList) {
            dtoList.add(DirectoryOfCargoNomenclaturesDto.addDirectoryOfCargoNomenclatures(directoryEntity));
        }
        return dtoList;
    }
}

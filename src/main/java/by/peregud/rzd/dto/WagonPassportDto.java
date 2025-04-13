package by.peregud.rzd.dto;

import by.peregud.rzd.enums.WagonType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import by.peregud.rzd.entity.WagonPassportEntity;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Information about the wagon passport")
public class WagonPassportDto {

    private int number;
    private WagonType type;
    private int tareWeight;
    private int loadCapacity;


    public static WagonPassportDto addWagonPassportDto(WagonPassportEntity wagonPassportEntity) {
        return WagonPassportDto.builder()
                .type(wagonPassportEntity.getType())
                .number(wagonPassportEntity.getNumber())
                .loadCapacity(wagonPassportEntity.getLoadCapacity())
                .tareWeight(wagonPassportEntity.getType().getTareWeight())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WagonPassportDto that = (WagonPassportDto) o;
        return number == that.number && tareWeight == that.tareWeight && loadCapacity == that.loadCapacity && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type, tareWeight, loadCapacity);
    }
}

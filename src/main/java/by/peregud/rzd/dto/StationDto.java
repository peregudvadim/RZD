package by.peregud.rzd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import by.peregud.rzd.entity.StationEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Information about the station")
public class StationDto {

    @JsonProperty("station_name")
    private String stationName;

    private List<String> number;

    public static StationDto addStationDto(StationEntity stationEntity) {
        return StationDto.builder()
                .stationName(stationEntity.getStationName())
                .number(getListOfNumber(stationEntity.getNumber()))
                .build();
    }

    public static List<String> getListOfNumber(List<Integer> integerList) {
        List<String> stringList = new ArrayList<>();
        for (Integer number : integerList) {
            stringList.add(String.format("Track №%d", number));
        }
        return stringList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationDto that = (StationDto) o;
        return stationName.equals(that.stationName) && number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, number);
    }
}

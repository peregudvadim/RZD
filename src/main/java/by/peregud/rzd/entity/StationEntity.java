package by.peregud.rzd.entity;

import by.peregud.rzd.dto.StationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "station_model")
public class StationEntity {

    /**
     * Station model (Stations, Station tracks)
     */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "station_name", unique = true)
    private String stationName;

    @ElementCollection
    private List<Integer> number;

    public static StationEntity addStation(StationDto stationDto) {
        return StationEntity.builder()
                .stationName(stationDto.getStationName())
                .number(getListOfNumber(stationDto.getNumber()))
                .build();
    }

    public static StationEntity updateStation(StationEntity stationEntity, StationDto stationDto) {
        return StationEntity.builder()
                .id(stationEntity.getId())
                .stationName(stationDto.getStationName())
                .number(getListOfNumber(stationDto.getNumber()))
                .build();
    }

    public static List<Integer> getListOfNumber(List<String> stringList) {
        List<Integer> integerList = new ArrayList<>();
        for (String str : stringList) {
            integerList.add(Integer.parseInt(str.substring(str.indexOf("№") + 1)));
        }
        return integerList;
    }
}

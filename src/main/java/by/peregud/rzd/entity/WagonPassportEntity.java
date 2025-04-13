package by.peregud.rzd.entity;

import by.peregud.rzd.dto.WagonPassportDto;
import by.peregud.rzd.enums.WagonType;
import by.peregud.rzd.enums.WagonTypeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "wagon_passport")
public class WagonPassportEntity {

    /**
     * •	Wagon passport (Number, Type, Tare weight, Load capacity)
     */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private int number;


    @Enumerated(EnumType.STRING)
    private WagonType type;

//    @Convert(converter = WagonTypeConverter.class)
//    private WagonType type;

    @Column(name = "tare_weight")
    private int tareWeight;

    @Column(name = "load_capacity")
    private int loadCapacity;

    @OneToMany(mappedBy = "wagonPassport",fetch = FetchType.LAZY)
    private List<ScaleEntity> scaleEntity;

    public static WagonPassportEntity addWagonPassport(WagonPassportDto wagonPassportDto) {
        return WagonPassportEntity.builder()
                .type(wagonPassportDto.getType())
                .number(wagonPassportDto.getNumber())
                .loadCapacity(wagonPassportDto.getType().getLoadCapacity())
                .tareWeight(wagonPassportDto.getType().getTareWeight())
                .build();
    }

    public static WagonPassportEntity updateWagonPassport(WagonPassportEntity wagonPassportEntity, WagonPassportDto wagonPassportDto) {
        return WagonPassportEntity.builder()
                .id(wagonPassportEntity.getId())
                .type(wagonPassportDto.getType())
                .number(wagonPassportDto.getNumber())
                .loadCapacity(wagonPassportDto.getType().getLoadCapacity())
                .tareWeight(wagonPassportDto.getType().getTareWeight())
                .build();
    }
}

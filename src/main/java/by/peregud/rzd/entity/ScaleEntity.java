package by.peregud.rzd.entity;

import by.peregud.rzd.dto.WagonPassportDto;
import by.peregud.rzd.dto.scale.ScaleCreateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "scale")
public class ScaleEntity {

    /**
     * List of wagons with the following attributes:
     * Serial number, Wagon number, Cargo nomenclature, Cargo weight in the wagon, Wagon weight
     */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "serial_number")
    private Integer serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scale_id")
    private FullScaleEntity fullScaleEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wagon_number")
    private WagonPassportEntity wagonPassport;


    @OneToMany(mappedBy = "scaleEntity",fetch = FetchType.LAZY)
    private List<DirectoryOfCargoNomenclaturesEntity> nomenclatures;


    @Column(name = "cargo_weight")
    private BigDecimal cargoWeight;


    @Column(name = "wagon_weight")
    private BigDecimal wagonWeight;

    public static ScaleEntity addScale(ScaleCreateDto scaleCreateDto,
                                       WagonPassportEntity wagonPassportEntity,
                                       List<DirectoryOfCargoNomenclaturesEntity> listDirectory) {
        return ScaleEntity.builder()
                .serialNumber(scaleCreateDto.getSerialNumber())
                .cargoWeight(scaleCreateDto.getCargoWeight())
                .nomenclatures(listDirectory)
                .wagonPassport(wagonPassportEntity)
                .wagonWeight(BigDecimal.valueOf(wagonPassportEntity.getTareWeight()))
                .build();
    }

    public static ScaleEntity updateScaleById(ScaleEntity scaleEntity,
                                              ScaleCreateDto scaleDto,
                                              WagonPassportEntity wagonPassportEntity,
                                              List<DirectoryOfCargoNomenclaturesEntity> listDirectory) {
        return ScaleEntity.builder()
                .id(scaleEntity.getId())
                .serialNumber(scaleDto.getSerialNumber())
                .wagonPassport(wagonPassportEntity)
                .nomenclatures(listDirectory)
                .cargoWeight(scaleDto.getCargoWeight())
                .wagonWeight(BigDecimal.valueOf(wagonPassportEntity.getTareWeight()))
                .build();
    }

    public static WagonPassportEntity getWagonPassportEntity(WagonPassportDto wagonPassportDto) {
        return WagonPassportEntity.addWagonPassport(wagonPassportDto);
    }
}

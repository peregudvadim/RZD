package by.peregud.rzd.entity;

import by.peregud.rzd.dto.fullscale.FullScaleCreateDto;
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
@Table(name = "full_scale")
public class FullScaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "composition_number", unique = true)
    private Integer compositionNumber;


    @OneToMany(mappedBy = "fullScaleEntity",fetch = FetchType.LAZY)
    private List<ScaleEntity> scales;

    public static FullScaleEntity addScale(FullScaleCreateDto fullScaleCreateDto,
                                           List<ScaleEntity> listScales) {
        return FullScaleEntity.builder()
                .compositionNumber(fullScaleCreateDto.getCompositionNumber())
                .scales(listScales)
                .build();
    }

    public static FullScaleEntity updateScaleById(FullScaleEntity fullScaleEntity,
                                                  FullScaleCreateDto fullScaleCreateDto,
                                                  List<ScaleEntity> listScales) {
        return FullScaleEntity.builder()
                .id(fullScaleEntity.getId())
                .compositionNumber(fullScaleCreateDto.getCompositionNumber())
                .scales(listScales)
                .build();
    }
}

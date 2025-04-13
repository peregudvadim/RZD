package by.peregud.rzd.dto.fullscale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import by.peregud.rzd.dto.scale.ScaleDto;
import by.peregud.rzd.entity.FullScaleEntity;
import by.peregud.rzd.entity.ScaleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Information about train compositions")
public class FullScaleDto {

    @JsonProperty("composition_number")
    private Integer compositionNumber;

    @JsonProperty("scale_id")
    private List<ScaleDto> scales;
    public static FullScaleDto addFullScale(FullScaleEntity fullScaleEntity) {
        return FullScaleDto.builder()
                .compositionNumber(fullScaleEntity.getCompositionNumber())
                .scales(getScaleDto(fullScaleEntity.getScales()))
                .build();
    }

    public static List<ScaleDto> getScaleDto(List<ScaleEntity> scaleEntities) {
        List<ScaleDto> dtos = new ArrayList<>();
        for (ScaleEntity scaleEntity : scaleEntities) {
            dtos.add(ScaleDto.addScale(scaleEntity));
        }
        return dtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullScaleDto that = (FullScaleDto) o;
        return compositionNumber.equals(that.compositionNumber) && scales.equals(that.scales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compositionNumber, scales);
    }
}

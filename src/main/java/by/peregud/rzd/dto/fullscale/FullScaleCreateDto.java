package by.peregud.rzd.dto.fullscale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Информация о составах поездов")
public class FullScaleCreateDto {


    @JsonProperty("composition_number")
    private Integer compositionNumber;

    @JsonProperty("scale_id")
    private List<Integer> scales;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullScaleCreateDto that = (FullScaleCreateDto) o;
        return compositionNumber.equals(that.compositionNumber) && scales.equals(that.scales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compositionNumber, scales);
    }
}

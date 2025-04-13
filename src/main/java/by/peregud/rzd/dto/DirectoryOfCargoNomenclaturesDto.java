package by.peregud.rzd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import by.peregud.rzd.entity.DirectoryOfCargoNomenclaturesEntity;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Cargo nomenclature")
public class DirectoryOfCargoNomenclaturesDto {

    private String code;

    @JsonProperty("shipping_name")
    private String shippingName;

    public static DirectoryOfCargoNomenclaturesDto addDirectoryOfCargoNomenclatures(
            DirectoryOfCargoNomenclaturesEntity dcn ) {
        return DirectoryOfCargoNomenclaturesDto.builder()
                .code(dcn.getCode())
                .shippingName(dcn.getShippingName())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryOfCargoNomenclaturesDto that = (DirectoryOfCargoNomenclaturesDto) o;
        return Objects.equals(this.code, that.code)&&Objects.equals(this.shippingName,that.shippingName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code, this.shippingName);
    }


}

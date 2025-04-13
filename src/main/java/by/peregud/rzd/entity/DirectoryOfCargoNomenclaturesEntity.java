package by.peregud.rzd.entity;

import by.peregud.rzd.dto.DirectoryOfCargoNomenclaturesDto;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Table(name = "directory_of_cargo_nomenclatures")
public class DirectoryOfCargoNomenclaturesEntity {

    /**
     * Cargo code, Cargo name
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(unique = true)
    private String code;

    @Column(name = "shipping_name", unique = true)
    private String shippingName;

    @ManyToOne
    @JoinColumn(name = "nomenclature_id")
    private ScaleEntity scaleEntity;

    public static DirectoryOfCargoNomenclaturesEntity addDirectoryOfCargoNomenclatures
            (DirectoryOfCargoNomenclaturesDto directory) {
        return DirectoryOfCargoNomenclaturesEntity.builder()
                .code(directory.getCode())
                .shippingName(directory.getShippingName())
                .build();
    }

    public static DirectoryOfCargoNomenclaturesEntity updateDirectoryOfCargoNomenclatures(
            DirectoryOfCargoNomenclaturesEntity directory,
            DirectoryOfCargoNomenclaturesDto directoryDto) {

        return DirectoryOfCargoNomenclaturesEntity.builder()
                .id(directory.getId())
                .shippingName(directoryDto.getShippingName())
                .code(directoryDto.getCode())
                .build();
    }
}



package by.peregud.rzd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import by.peregud.rzd.entity.ScaleEntity;

public interface ScaleRepository extends JpaRepository<ScaleEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE scale SET serial_number = NULL WHERE scale_id = :scale_id", nativeQuery = true)
    void deleteSerialNumber(@Param("scale_id") Integer scaleId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE scale s " +
            "JOIN (SELECT id FROM scale WHERE scale_id = :scale_id AND serial_number IS NULL ORDER BY id) AS subquery " +
            "ON s.id = subquery.id " +
            "SET s.serial_number = COALESCE((SELECT MAX(serial_number) FROM scale WHERE scale_id = :scale_id), 0) + (@row := @row + 1) " +
            "WHERE s.scale_id = :scale_id",
            nativeQuery = true)
    void updateSerialNumber(@Param("scale_id") Integer scaleId);


}


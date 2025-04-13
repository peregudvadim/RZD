package by.peregud.rzd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import by.peregud.rzd.entity.WagonPassportEntity;

import java.util.Optional;

public interface WagonPassportRepository extends JpaRepository<WagonPassportEntity, Integer> {


    boolean existsByNumber(int number);

    Optional<WagonPassportEntity> findByNumber(int number);
}

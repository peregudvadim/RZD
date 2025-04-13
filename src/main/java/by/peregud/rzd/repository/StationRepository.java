package by.peregud.rzd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import by.peregud.rzd.entity.StationEntity;

public interface StationRepository extends JpaRepository<StationEntity, Integer> {
}

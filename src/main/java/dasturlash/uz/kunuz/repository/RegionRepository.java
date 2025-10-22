package dasturlash.uz.kunuz.repository;

import dasturlash.uz.kunuz.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

    boolean existsByRegionKey(String regionKey);

    Optional<RegionEntity> findByRegionKey(String regionKey);
}

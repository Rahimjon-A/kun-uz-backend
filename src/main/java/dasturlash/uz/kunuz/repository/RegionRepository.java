package dasturlash.uz.kunuz.repository;

import dasturlash.uz.kunuz.dto.result.LangMapper;
import dasturlash.uz.kunuz.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

    boolean existsByRegionKey(String regionKey);

    Optional<RegionEntity> findByRegionKey(String regionKey);

    @Query("SELECT c.id AS id, " +
            "CASE :lang " +
            "   WHEN 'UZ' THEN c.nameUz " +
            "   WHEN 'RU' THEN c.nameRu " +
            "   WHEN 'EN' THEN c.nameEn " +
            "END AS name, " +
            "c.orderNumber AS orderNumber, " +
            "c.regionKey AS key " +
            "FROM RegionEntity c " +
            "WHERE c.visible = true order by orderNumber asc")
    List<LangMapper> getByLang(@Param("lang") String lang);
}

package dasturlash.uz.kunuz.repository;

import dasturlash.uz.kunuz.dto.result.LangMapper;
import dasturlash.uz.kunuz.entity.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SectionRepository
        extends JpaRepository<SectionEntity, Integer>, 
        PagingAndSortingRepository<SectionEntity, Integer> {

    boolean existsBySectionKey(String sectionKey);

    Optional<SectionEntity> findBySectionKey(String sectionKey);

    @Query("SELECT c.id AS id, " +
            "CASE :lang " +
            "   WHEN 'UZ' THEN c.nameUz " +
            "   WHEN 'RU' THEN c.nameRu " +
            "   WHEN 'EN' THEN c.nameEn " +
            "END AS name, " +
            "c.orderNumber AS orderNumber, " +
            "c.sectionKey AS key " +
            "FROM SectionEntity c " +
            "WHERE c.visible = true order by orderNumber asc")
    List<LangMapper> getByLang(@Param("lang") String lang);
}

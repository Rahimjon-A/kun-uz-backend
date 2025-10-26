package dasturlash.uz.kunuz.repository;

import dasturlash.uz.kunuz.dto.result.LangMapper;
import dasturlash.uz.kunuz.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

        boolean existsByCategoryKey(String categoryKey);

        Optional<CategoryEntity> findByCategoryKey(String categoryKey);

        @Query("SELECT c.id AS id, " +
                "CASE :lang " +
                "   WHEN 'UZ' THEN c.nameUz " +
                "   WHEN 'RU' THEN c.nameRu " +
                "   WHEN 'EN' THEN c.nameEn " +
                "END AS name, " +
                "c.orderNumber AS orderNumber, " +
                "c.categoryKey AS key " +
                "FROM CategoryEntity c " +
                "WHERE c.visible = true order by orderNumber asc")
        List<LangMapper> getByLang(@Param("lang") String lang);
}

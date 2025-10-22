package dasturlash.uz.kunuz.repository;

import dasturlash.uz.kunuz.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

        boolean existsByCategoryKey(String categoryKey);

        Optional<CategoryEntity> findByCategoryKey(String categoryKey);
}

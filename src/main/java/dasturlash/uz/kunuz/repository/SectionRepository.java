package dasturlash.uz.kunuz.repository;

import dasturlash.uz.kunuz.entity.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SectionRepository
        extends JpaRepository<SectionEntity, Integer>, 
        PagingAndSortingRepository<SectionEntity, Integer> {

    boolean existsBySectionKey(String sectionKey);

    Optional<SectionEntity> findBySectionKey(String sectionKey);
}

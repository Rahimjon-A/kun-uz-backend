package dasturlash.uz.kunuz.repository;

import dasturlash.uz.kunuz.entity.ProfileEntity;
import dasturlash.uz.kunuz.enums.ProfileStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository
        extends JpaRepository<ProfileEntity, Integer>,
        PagingAndSortingRepository<ProfileEntity, Integer> {

    boolean existsByUsernameAndVisibleIsTrue(String username);

    Optional<ProfileEntity> findByUsernameAndVisibleIsTrue(String username);

    @Modifying
    @Query("update ProfileEntity set status =?1 where username =?2")
    void setStatusByUsername(ProfileStatus status, String username);

}

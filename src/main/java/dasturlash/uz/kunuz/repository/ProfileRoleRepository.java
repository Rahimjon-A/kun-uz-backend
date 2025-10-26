package dasturlash.uz.kunuz.repository;

import dasturlash.uz.kunuz.entity.ProfileRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRoleRepository extends JpaRepository<ProfileRoleEntity, Integer> {

    List<ProfileRoleEntity> findAllByProfileId(Integer profileId);
    
    void deleteAllByProfileId(Integer profileId);

}

package dasturlash.uz.kunuz.service;

import dasturlash.uz.kunuz.entity.ProfileRoleEntity;
import dasturlash.uz.kunuz.enums.ProfileRoleEnum;
import dasturlash.uz.kunuz.repository.ProfileRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileRoleService {

    @Autowired
    private ProfileRoleRepository roleRepository;

    public void create(Integer id, List<ProfileRoleEnum> roles) {
        for (ProfileRoleEnum role : roles) {
            ProfileRoleEntity roleEntity = new ProfileRoleEntity();
            roleEntity.setProfileId(id);
            roleEntity.setRole(role);
            roleRepository.save(roleEntity);
        }
    }

    public List<ProfileRoleEnum> findAll(Integer id) {
        return roleRepository.findAllByProfileId(id)
                .stream()
                .map(ProfileRoleEntity::getRole)
                .toList();
    }

    public void deleteRolesByProfileId(Integer id) {
        roleRepository.deleteAllByProfileId(id);
    }
}

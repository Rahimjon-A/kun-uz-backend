package dasturlash.uz.kunuz.service;

import dasturlash.uz.kunuz.dto.profile.ProfileFilterDto;
import dasturlash.uz.kunuz.dto.profile.ProfileDto;
import dasturlash.uz.kunuz.dto.result.ResultFilterDto;
import dasturlash.uz.kunuz.entity.ProfileEntity;
import dasturlash.uz.kunuz.enums.ProfileStatus;
import dasturlash.uz.kunuz.exception.EntityException;
import dasturlash.uz.kunuz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    @Autowired
    private ProfileRoleService roleService;

    @Autowired
    private FilterService filterService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ProfileDto create(ProfileDto dto) {
        boolean exists = repository.existsByUsernameAndVisibleIsTrue(dto.getUsername());

        if (exists) {
            throw new EntityException("Username already exists");
        }

        ProfileEntity entity = new ProfileEntity();

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        repository.save(entity);
        roleService.create(entity.getId(), dto.getRoles());

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public ProfileEntity updateByAdmin(Integer id, ProfileDto dto) {
        Optional<ProfileEntity> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new EntityException("Profile not found");
        }

        ProfileEntity entity = dtoToEntity(optional.get(), dto);
        repository.save(entity);
        return entity;
    }

    public void delete(Integer id) {
        Optional<ProfileEntity> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new EntityException("Profile not found");
        }

        ProfileEntity entity = optional.get();
        entity.setVisible(false);
        repository.save(entity);
    }

    public PageImpl<ProfileDto> getPageList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProfileEntity> all = repository.findAll(pageable);

        List<ProfileDto> dtos = new ArrayList<>();
        for (ProfileEntity entity : all.getContent()) {
            ProfileDto dto = new ProfileDto();

            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setUsername(entity.getUsername());
            dto.setPassword(entity.getPassword());
            dto.setRoles(roleService.findAll(entity.getId()));
            dto.setCreatedDate(entity.getCreatedDate());

            dtos.add(dto);
        }

        return new PageImpl<>(dtos, pageable, all.getTotalElements());
    }

    private ProfileEntity dtoToEntity(ProfileEntity entity, ProfileDto dto) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setStatus(dto.getStatus());
        entity.setPhotoId(null);
        return entity;
    }
    private List<ProfileDto> entityToDto(List<ProfileEntity> entities) {
        List<ProfileDto> dtos = new ArrayList<>();
        for (ProfileEntity entity : entities) {
            ProfileDto dto = new ProfileDto();
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setUsername(entity.getUsername());
            dto.setPassword(entity.getPassword());
            dto.setRoles(roleService.findAll(entity.getId()));
            dto.setCreatedDate(entity.getCreatedDate());
            dtos.add(dto);
        }
        return dtos;
    }

    public PageImpl<ProfileDto> filter(ProfileFilterDto dto, int page, int size) {
        ResultFilterDto<ProfileEntity> entities = filterService.filterProfile(dto, page, size);
        return new PageImpl<>(
                entityToDto(entities.getContent()),
                PageRequest.of(page, size),
                entities.totalElements
        );
    }

    public void setStatusByUsername(ProfileStatus status, String username) {
        repository.setStatusByUsername(status, username);
    }

}

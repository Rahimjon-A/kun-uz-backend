package dasturlash.uz.kunuz.service;

import dasturlash.uz.kunuz.dto.result.LangMapper;
import dasturlash.uz.kunuz.dto.result.LangResponse;
import dasturlash.uz.kunuz.dto.RegionDto;
import dasturlash.uz.kunuz.entity.RegionEntity;
import dasturlash.uz.kunuz.enums.Lang;
import dasturlash.uz.kunuz.exception.EntityException;
import dasturlash.uz.kunuz.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    private final RegionRepository repository;

    public RegionService(RegionRepository repository) {
        this.repository = repository;
    }


    public RegionDto create(RegionDto dto) {
        boolean exists = repository.existsByRegionKey(dto.getRegionKey().toLowerCase());
        if (exists) {
            throw new EntityException("Region key exists: " + dto.getRegionKey());
        }
        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setRegionKey(dto.getRegionKey().toLowerCase());
        repository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public RegionDto update(RegionDto dto) {

        Optional<RegionEntity> optional = repository.findByRegionKey(dto.getRegionKey().toLowerCase());

        if (optional.isEmpty()) {
            throw new EntityException("Region not found");
        }

        RegionEntity entity = optional.get();
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setRegionKey(dto.getRegionKey().toLowerCase());
        entity.setOrderNumber(dto.getOrderNumber());

        repository.save(entity);

        return dto;
    }

    public void delete(Integer id) {
        Optional<RegionEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityException("Region not found");
        }

        RegionEntity entity = optional.get();
        entity.setVisible(false);
        repository.save(entity);
    }

    public List<RegionEntity> getAllByAdmin() {
        return repository.findAll();
    }

    public List<LangMapper> getRegionsByLang(Lang lang) {
        return repository.getByLang(lang.name());

    }

    public Integer getIdByKey(String key) {
        Optional<RegionEntity> optional = repository.findByRegionKey(key.toLowerCase());
        return optional.get().getId();
    }

}

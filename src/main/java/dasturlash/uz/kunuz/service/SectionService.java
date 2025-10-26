package dasturlash.uz.kunuz.service;

import dasturlash.uz.kunuz.dto.result.LangMapper;
import dasturlash.uz.kunuz.dto.result.LangResponse;
import dasturlash.uz.kunuz.dto.SectionDto;
import dasturlash.uz.kunuz.entity.SectionEntity;
import dasturlash.uz.kunuz.enums.Lang;
import dasturlash.uz.kunuz.exception.EntityException;
import dasturlash.uz.kunuz.repository.SectionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    private final SectionRepository repository;

    public SectionService(SectionRepository repository) {
        this.repository = repository;
    }

    public SectionDto create(SectionDto dto) {
        boolean exists = repository.existsBySectionKey(dto.getSectionKey().toLowerCase());
        if (exists) {
            throw new EntityException("Region key exists: " + dto.getSectionKey());
        }
        SectionEntity entity = new SectionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setSectionKey(dto.getSectionKey().toLowerCase());
        repository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public SectionDto update(SectionDto dto) {
        Optional<SectionEntity> optional = repository.findBySectionKey(dto.getSectionKey());

        if (optional.isEmpty()) {
            throw new EntityException("Region not found");
        }

        SectionEntity entity = optional.get();
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setSectionKey(dto.getSectionKey());
        entity.setOrderNumber(dto.getOrderNumber());

        repository.save(entity);

        return dto;
    }

    public void delete(Integer id) {
        Optional<SectionEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityException("Region not found");
        }

        SectionEntity entity = optional.get();
        entity.setVisible(false);
        repository.save(entity);
    }


    public List<LangMapper> getRegionsByLang(Lang lang) {
        return  repository.getByLang(lang.name());
    }

    public PageImpl<SectionEntity> getAllByAdmin(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<SectionEntity> all = repository.findAll(pageable);

        return new PageImpl<>(all.getContent(), pageable, all.getTotalElements());
    }
}

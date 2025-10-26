package dasturlash.uz.kunuz.service;

import dasturlash.uz.kunuz.dto.CategoryDto;
import dasturlash.uz.kunuz.dto.result.LangMapper;
import dasturlash.uz.kunuz.dto.result.LangResponse;
import dasturlash.uz.kunuz.entity.CategoryEntity;
import dasturlash.uz.kunuz.enums.Lang;
import dasturlash.uz.kunuz.exception.EntityException;
import dasturlash.uz.kunuz.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public CategoryDto create(CategoryDto dto) {
        boolean exists = repository.existsByCategoryKey(dto.getCategoryKey().toLowerCase());
        if (exists) {
            throw new EntityException("Region key exists: " + dto.getCategoryKey());
        }
        CategoryEntity entity = new CategoryEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setCategoryKey(dto.getCategoryKey().toLowerCase());
        repository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public CategoryDto update(CategoryDto dto) {
        Optional<CategoryEntity> optional = repository.findByCategoryKey(dto.getCategoryKey());

        if (optional.isEmpty()) {
            throw new EntityException("Categroy not found");
        }

        CategoryEntity entity = optional.get();
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setCategoryKey(dto.getCategoryKey());
        entity.setOrderNumber(dto.getOrderNumber());

        repository.save(entity);

        return dto;
    }

    public void delete(Integer id) {
        Optional<CategoryEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityException("Category not found");
        }

        CategoryEntity entity = optional.get();
        entity.setVisible(false);
        repository.save(entity);
    }

    public List<CategoryEntity> getAllByAdmin() {
        return repository.findAll();
    }

    public List<LangMapper> getRegionsByLang(Lang lang) {
        return repository.getByLang(lang.name());
    }
}

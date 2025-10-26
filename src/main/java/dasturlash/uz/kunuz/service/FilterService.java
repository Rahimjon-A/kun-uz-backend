package dasturlash.uz.kunuz.service;

import dasturlash.uz.kunuz.dto.profile.ProfileFilterDto;
import dasturlash.uz.kunuz.dto.result.ResultFilterDto;
import dasturlash.uz.kunuz.entity.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilterService {

    @Autowired
    private EntityManager entityManager;

    public ResultFilterDto<ProfileEntity> filterProfile(ProfileFilterDto filter, int page, int size) {
        StringBuilder conditionBuilder = new StringBuilder(" where visible = true ");

        Map<String, Object> params = new HashMap<>();

        if (filter.getName() != null) {
            conditionBuilder.append(" and s.name like :name ");
            params.put("name", "%" + filter.getName() + "%");
        }
        if (filter.getSurname() != null) {
            conditionBuilder.append(" and s.surname like :surname ");
            params.put("surname", "%" + filter.getSurname() + "%");
        }
        if (filter.getUsername() != null) {
            conditionBuilder.append(" and s.username = :username ");
            params.put("username", filter.getUsername());
        }
        if (filter.getCreated_date_from() != null && filter.getCreated_date_to() != null) {
            conditionBuilder.append(" and s.created_date between :date_from and :date_to ");
            params.put("date_from", filter.getCreated_date_from());
            params.put("date_to", filter.getCreated_date_to());
        }

        String selectSql = "From ProfileEntity s" + conditionBuilder;
        String countSql = "Select count(s) From ProfileEntity s" + conditionBuilder;

        Query selectQuery = entityManager.createQuery(selectSql);
        selectQuery.setFirstResult(page * size);
        selectQuery.setMaxResults(size);

        Query countQuery = entityManager.createQuery(countSql);

        params.forEach((key, value) -> {
            selectQuery.setParameter(key, value);
            countQuery.setParameter(key, value);
        });

        List<ProfileEntity> entityList = selectQuery.getResultList();
        Long totalElements = (Long) countQuery.getSingleResult();

        return new ResultFilterDto<>(entityList, totalElements);
    }


}

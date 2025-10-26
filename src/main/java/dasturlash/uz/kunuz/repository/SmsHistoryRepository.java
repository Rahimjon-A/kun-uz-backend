package dasturlash.uz.kunuz.repository;

import dasturlash.uz.kunuz.dto.responses.SmsHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmsHistoryRepository extends JpaRepository<SmsHistoryEntity, String> {

    Optional<SmsHistoryEntity> findTopByPhoneNumberOrderByCreatedDateDesc(String phoneNumber);

}

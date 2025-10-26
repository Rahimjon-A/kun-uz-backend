package dasturlash.uz.kunuz.service;

import dasturlash.uz.kunuz.dto.responses.SmsHistoryEntity;
import dasturlash.uz.kunuz.exception.EntityException;
import dasturlash.uz.kunuz.repository.SmsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SmsHistoryService {

    @Autowired
    private SmsHistoryRepository smsHistoryRepository;

    public void save(String phone, String body, String code) {
        SmsHistoryEntity entity = new SmsHistoryEntity();
        entity.setPhoneNumber(phone);
        entity.setBody(body);
        entity.setCode(code);
        entity.setCreatedDate(LocalDateTime.now());
        smsHistoryRepository.save(entity);
    }

    public boolean isSmsSendToPhone(String phone, String code) {
        SmsHistoryEntity smsHistoryEntity = getSmsByPhone(phone);
        // time (2minute) TODO
        // attempt count (3) TODO
        if (!code.equals(smsHistoryEntity.getCode())) {
            return false;
        }
        return true;
    }

    public SmsHistoryEntity getSmsByPhone(String phoneNumber) {
        Optional<SmsHistoryEntity> optional = smsHistoryRepository.findTopByPhoneNumberOrderByCreatedDateDesc(phoneNumber);
        if (optional.isEmpty()) {
            throw new EntityException("Invalid phone number");
        }
        return optional.get();
    }

}

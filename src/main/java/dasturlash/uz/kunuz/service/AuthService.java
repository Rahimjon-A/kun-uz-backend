package dasturlash.uz.kunuz.service;

import dasturlash.uz.kunuz.dto.VerificationBySmsDto;
import dasturlash.uz.kunuz.dto.profile.RegistrationDto;
import dasturlash.uz.kunuz.dto.responses.SmsHistoryEntity;
import dasturlash.uz.kunuz.entity.ProfileEntity;
import dasturlash.uz.kunuz.enums.ProfileRoleEnum;
import dasturlash.uz.kunuz.enums.ProfileStatus;
import dasturlash.uz.kunuz.exception.EntityException;
import dasturlash.uz.kunuz.repository.ProfileRepository;
import dasturlash.uz.kunuz.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileRoleService profileRoleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SmsSenderService smsSenderService;

    @Autowired
    private SmsHistoryService smsHistoryService;

    @Autowired
    private ProfileService profileService;

    public String registration(RegistrationDto dto) { //
        // name = Toshmat, username = mazgi
        // name = Eshmat, username = mazgi
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleIsTrue(dto.getUsername());
        if (optional.isPresent()) { //
            ProfileEntity existsProfile = optional.get();
            if (existsProfile.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
                profileRoleService.deleteRolesByProfileId(existsProfile.getId());
                profileRepository.deleteById(existsProfile.getId()); // delete
            } else {
                throw new EntityException("Username already exists");
            }
        }

        // create profile
        ProfileEntity profile = new ProfileEntity();
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setUsername(dto.getUsername());
        profile.setPassword(passwordEncoder.encode(dto.getPassword()));
        profile.setVisible(true);
        profile.setStatus(ProfileStatus.NOT_ACTIVE);
        profileRepository.save(profile);

        // save roles
        profileRoleService.create(profile.getId(), List.of(ProfileRoleEnum.ROLE_USER));
        // send verification code

        smsSenderService.sendRegistrationSMS(profile.getUsername());

        return "Sms code jo'natildi mazgi.";
    }

    public String verificationBySms(VerificationBySmsDto dto) {
        if (smsHistoryService.isSmsSendToPhone(dto.getPhoneNumber(), dto.getCode())) {
            profileService.setStatusByUsername(ProfileStatus.ACTIVE, dto.getPhoneNumber());
            return "Verification Success!";
        }
        throw new EntityException("Wrong sms code");
    }




}

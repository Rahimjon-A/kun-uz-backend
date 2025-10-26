package dasturlash.uz.kunuz.controller;

import dasturlash.uz.kunuz.dto.VerificationBySmsDto;
import dasturlash.uz.kunuz.dto.profile.RegistrationDto;
import dasturlash.uz.kunuz.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationDto dto) {
        return ResponseEntity.ok(authService.registration(dto));
    }

    @PutMapping("/registration/sms/verification")
    public ResponseEntity<String> verificationBySms(@RequestBody VerificationBySmsDto dto) {
        return ResponseEntity.ok(authService.verificationBySms(dto));
    }

}

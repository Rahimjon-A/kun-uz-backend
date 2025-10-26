package dasturlash.uz.kunuz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationBySmsDto {
    @NotBlank(message = "Phone number required")
    private String phoneNumber;
    @NotBlank(message = "Code required")
    private String code;
}


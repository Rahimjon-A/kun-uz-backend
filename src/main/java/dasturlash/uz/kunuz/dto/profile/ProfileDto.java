package dasturlash.uz.kunuz.dto.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import dasturlash.uz.kunuz.enums.ProfileRoleEnum;
import dasturlash.uz.kunuz.enums.ProfileStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDto {

    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String surname;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

    private ProfileStatus status ;

    @NotEmpty
    private List<ProfileRoleEnum> roles;

    private LocalDateTime createdDate;
}



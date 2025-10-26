package dasturlash.uz.kunuz.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileFilterDto {
    private String name;
    private String surname;
    private String username;
    private String role;
    private LocalDate created_date_from;
    private LocalDate created_date_to;
}

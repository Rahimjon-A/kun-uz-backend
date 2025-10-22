package dasturlash.uz.kunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private Integer id;

    @NotNull
    private Integer orderNumber;

    @NotNull
    @NotBlank
    private String nameUz;

    @NotNull
    @NotBlank
    private String nameRu;

    @NotNull
    @NotBlank
    private String nameEn;

    @NotNull
    @NotBlank
    private String categoryKey;

    private LocalDateTime createdDate;
}

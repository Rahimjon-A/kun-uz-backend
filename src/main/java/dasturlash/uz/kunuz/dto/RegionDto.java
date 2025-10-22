package dasturlash.uz.kunuz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
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
public class RegionDto {

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
    private String regionKey;

    private LocalDateTime createdDate;

    public RegionDto(Integer orderNumber, String nameUz, String nameRu, String nameEn, String regionKey) {
        this.orderNumber = orderNumber;
        this.nameUz = nameUz;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.regionKey = regionKey;
    }
}

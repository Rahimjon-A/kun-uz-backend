package dasturlash.uz.kunuz.dto.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LangResponse {
    private Integer id;
    private String key;
    private String name;
}


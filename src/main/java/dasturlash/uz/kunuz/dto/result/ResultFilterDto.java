package dasturlash.uz.kunuz.dto.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResultFilterDto<T> {
    public List<T> content;
    public Long totalElements;
}

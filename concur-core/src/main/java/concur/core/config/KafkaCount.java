package concur.core.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaCount {
    private Long userId;
    private Integer delta;

    public String value() {
        return userId + ":" + delta;
    }
}

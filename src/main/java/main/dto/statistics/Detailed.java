package main.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.model.Status;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detailed {
    private String url;
    private String name;
    private Status status;
    private long statusTime;
    private String error;
    private long pages;
    private long lemmas;
}

package main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private final boolean result = false;
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }
}

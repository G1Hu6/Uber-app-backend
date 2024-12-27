package com.uber.advices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private HttpStatus httpStatus;

    private String message;

    private List<String> subErrors;

    public ApiError(ApiError apiError) {
    }
}

package com.technical.test.wastemanageraddress.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing a standard API error response, including an HTTP status code and error details in a ProblemDetail object.
 * <p>
 * The class is annotated with @JsonInclude(JsonInclude.Include.NON_NULL) to exclude fields with null values during serialization.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private ErrorDataResponse error;

    private Integer resultCode;

    private String message;

}
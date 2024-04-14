package com.technical.test.wastemanager.exception;

import com.technical.test.wastemanager.util.ConstantsCode;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebInputException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<Object> handleRequestInputDataException(ServerWebInputException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(HttpStatus.BAD_REQUEST.toString())
                .resultCode(ConstantsCode.SYSTEM_ERROR)
                .error(ErrorDataResponse.builder().reason("INPUT").message(ex.getMessage()).build())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleRequestInputDataException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(HttpStatus.BAD_REQUEST.toString())
                .resultCode(ConstantsCode.SYSTEM_ERROR)
                .error(ErrorDataResponse.builder().reason("INPUT").message(ex.getMessage()).build())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(HttpStatus.NOT_FOUND.toString())
                .resultCode(ConstantsCode.BUSSINESS_ERROR)
                .error(ErrorDataResponse.builder().reason("DATA").message(ex.getMessage()).build())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleNotFoundException(DataAccessException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .resultCode(ConstantsCode.SYSTEM_ERROR)
                .error(ErrorDataResponse.builder().reason("DATA_ACCESS").message(ex.getMessage()).build())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

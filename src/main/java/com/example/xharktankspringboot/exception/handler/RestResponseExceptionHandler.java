package com.example.xharktankspringboot.exception.handler;

import com.example.xharktankspringboot.dto.ResponseDTO;
import com.example.xharktankspringboot.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.example.xharktankspringboot.dto.ResponseDTOUtil.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice(annotations = RestController.class)
public class RestResponseExceptionHandler {

    private final String INTERNAL_SERVER_MESSAGE = "There is some internal system error. Kindly contact system administrator.";

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ResponseDTO> resourceNotFoundHandler(ResourceNotFoundException ex) {
        return new ResponseEntity(createEmptySuccessResponse(ex.getMessage(), NOT_FOUND), NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseDTO> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(createErrorResponse(INTERNAL_SERVER_MESSAGE, INTERNAL_SERVER_ERROR), INTERNAL_SERVER_ERROR);
    }
}

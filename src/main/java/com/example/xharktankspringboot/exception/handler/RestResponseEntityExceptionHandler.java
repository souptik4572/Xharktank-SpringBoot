package com.example.xharktankspringboot.exception.handler;

import com.example.xharktankspringboot.domain.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice(annotations = RestController.class)
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<RestResponse> handleRuntimeException(RuntimeException ex) {
        RestResponse response = RestResponse.builder()
                .statusCode(INTERNAL_SERVER_ERROR)
                .response("There is some internal system error. Kindly contact system administrator.")
                .build();

        return new ResponseEntity(response, INTERNAL_SERVER_ERROR);
    }
}

package com.example.xharktankspringboot.dto;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class ResponseDTOUtil {

    public static ResponseDTO createSuccessResponse(final Object data, final HttpStatus statusCode) {
        return ResponseDTO.builder().data(data).statusCode(statusCode.value()).build();
    }

    public static ResponseDTO createEmptySuccessResponse(final String message, final HttpStatus statusCode) {
        return ResponseDTO.builder().message(message).statusCode(statusCode.value()).build();
    }

    public static ResponseDTO createErrorResponse(final String message, final HttpStatus statusCode) {
        return createEmptySuccessResponse(message, statusCode);
    }
}

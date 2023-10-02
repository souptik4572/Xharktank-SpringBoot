package com.example.xharktankspringboot.dto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseDTOUtil {

    public static ResponseDTO createSuccessResponse(final Object data, final String message) {
        return ResponseDTO.builder().data(data).message(message).build();
    }

    public static ResponseDTO createEmptySuccessResponse(final String message) {
        return ResponseDTO.builder().message(message).build();
    }

    public static ResponseDTO createErrorResponse(final String message, final Integer statusCode) {
        return ResponseDTO.builder().message(message).statusCode(statusCode).build();
    }
}

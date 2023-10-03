package com.example.xharktankspringboot.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class RestResponse {
    private HttpStatus statusCode;
    private Object response;
}

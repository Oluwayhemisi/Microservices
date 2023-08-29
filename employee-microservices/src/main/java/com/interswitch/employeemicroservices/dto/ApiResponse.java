package com.interswitch.employeemicroservices.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ApiResponse {
    String message;
    private Object data;
}

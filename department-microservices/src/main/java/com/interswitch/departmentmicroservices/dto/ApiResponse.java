package com.interswitch.departmentmicroservices.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    String message;
    private Object data;
}

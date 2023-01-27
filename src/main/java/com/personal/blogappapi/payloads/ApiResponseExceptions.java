package com.personal.blogappapi.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// class contains API Response for only message Exception
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponseExceptions {
    private String message;
}

package com.personal.blogappapi.payloads;

import lombok.Data;

@Data
public class JwtAuthResuest {
    private String username;
    private String password;
}

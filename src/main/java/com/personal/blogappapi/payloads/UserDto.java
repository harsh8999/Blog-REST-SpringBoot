package com.personal.blogappapi.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty
    private String name;

    @Email(message = "Email address is not valid !!!")
    private String email;

    @NotEmpty
    @Size(min = 8, message = "Password must be greater than 8 characters")
    private String password;

    @NotEmpty
    private String about;

}

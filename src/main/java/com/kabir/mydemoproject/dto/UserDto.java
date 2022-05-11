package com.kabir.mydemoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}

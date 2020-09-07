package com.myblog.demo.Dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    @NotBlank(message = "username 不能为空")
    private String username;

    @NotBlank(message = "password 不能为空")
    private String password;
}

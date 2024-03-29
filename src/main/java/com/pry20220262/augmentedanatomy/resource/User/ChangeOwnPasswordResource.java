package com.pry20220262.augmentedanatomy.resource.User;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChangeOwnPasswordResource {
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be null")
    private String oldPassword;
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be null")
    private String newPassword;
}

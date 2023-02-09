package com.pry20220262.augmentedanatomy.resource;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserSaveResource {
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be null")
    private String password;
}

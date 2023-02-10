package com.pry20220262.augmentedanatomy.resource.User;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserPinResource {
    @NotNull(message = "PIN cannot be null")
    @NotBlank(message = "PIN cannot be null")
    private String pin;
    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be null")
    private String email;
}

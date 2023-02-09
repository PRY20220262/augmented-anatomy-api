package com.pry20220262.augmentedanatomy.resource.User;

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

    @NotNull(message = "Phone cannot be null")
    @NotBlank(message = "Phone cannot be null")
    private String phone;

    @NotNull(message = "FullName cannot be null")
    @NotBlank(message = "FullName cannot be null")
    private String fullName;

    private Boolean isStudent;


}

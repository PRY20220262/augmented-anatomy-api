package com.pry20220262.augmentedanatomy.resource.User;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserProfileSaveResource {
    @NotNull(message = "Phone cannot be null")
    @NotBlank(message = "Phone cannot be null")
    private String phone;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Birthday cannot be null")
    @NotBlank(message = "Birthday cannot be null")
    private LocalDate birthday;
}

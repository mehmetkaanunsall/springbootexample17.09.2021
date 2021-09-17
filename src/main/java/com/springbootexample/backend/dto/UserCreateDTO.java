package com.springbootexample.backend.dto;

import com.springbootexample.backend.validator.UniqueUserName;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDTO {

    @NotNull(message = "{backend.constraints.username.NotNull.message}")
    @Size(min = 4, max = 24, message = "{backend.constraints.username.Size.message}")
    @UniqueUserName
    private String userName;

    @NotNull(message = "{backend.constraints.firstname.NotNull.message}")
    @Size(min = 2, max = 50, message = "{backend.constraints.firstname.Size.message}")
    private String firstName;

    @NotNull(message = "{backend.constraints.lastname.NotNull.message}")
    @Size(min = 3, max = 50, message = "{backend.constraints.lastname.Size.message}")
    private String lastName;

}

package com.challenge.user.model;


import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Valid
@Data
@Builder
public class UserRequestSto {

    @NotEmpty(message = "firstName cannot be empty")
    private String firstName;

    @NotEmpty(message = "lastName cannot be empty")
    private String lastName;

    @NotEmpty(message = "email cannot be empty")
    private String email;

}

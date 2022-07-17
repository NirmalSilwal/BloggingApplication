package com.eaproject.blog.payloads;
// user data transfer object

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    // we can  expose the data in this UserDto directly to the user / outer world / APIs
    // we don't want to expose our entity directly, so we're using this DTO

    private int id;

    @NotEmpty
    @Size(min = 4, message = "user name must be at least 4 characters")
    private String name;

    @Email(message = "your email address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "password must be min of 3 chars and max of 10 characters")
    private String password;

    @NotEmpty
    private String about;
}

package com.eaproject.blog.payloads;
// user data transfer object

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    // we can  expose the data in this UserDto directly to the user / outer world / APIs
    // we don't want to expose our entity directly so we're using this DTO
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}

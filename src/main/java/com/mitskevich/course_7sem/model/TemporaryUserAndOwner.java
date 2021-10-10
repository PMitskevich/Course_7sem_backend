package com.mitskevich.course_7sem.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class TemporaryUserAndOwner {

    @NotNull
    @Size(min = 4, max = 12)
    private String username;

    @NotNull
    @Size(min = 4)
    private String password;

    @NotNull
    @Size(min = 4)
    private String repeatPassword;

    @NotNull
    @Size(min = 2, max = 16)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 16)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 20)
    private String patronymic;

    @NotNull
    @Size(min = 2)
    private String address;

    @NotNull
    @Pattern(regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$")
    private String phone;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
    private String email;
}

package com.challenge.payload;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ysand
 */
public class UserCreateRequest {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @Valid
    @NotEmpty
    private List<PhoneCreateRequest> phones = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<PhoneCreateRequest> getPhones() {
        return phones;
    }

}

package com.challenge.payload;

import lombok.ToString;

/**
 *
 * @author ysand
 */
@ToString
public class Login {
    
    private String jwtToken;
    private String email;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}

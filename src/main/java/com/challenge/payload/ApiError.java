package com.challenge.payload;

import lombok.Getter;

/**
 *
 * @author ysand
 */
@Getter
public class ApiError {

    private String mensaje;

    public ApiError(String message) {
        this.mensaje = message;
    }

}

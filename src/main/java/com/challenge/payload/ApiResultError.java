package com.challenge.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author ysand
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApiResultError extends Result {

    private ApiError error;

    public ApiResultError() {
        super();
        this.error = new ApiError("Ha ocurrido un error desconocido");
    }

    public ApiResultError(String errorMessage) {
        super();
        this.error = new ApiError(errorMessage);
    }

}

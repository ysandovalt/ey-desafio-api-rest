package com.challenge.handler;

import com.challenge.payload.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author ysand
 * @param <T>
 */
public class ApiResponseHandler<T> {

    public ResponseEntity<ApiResult<T>> handleResponse(T payload, HttpStatus status) {
        return new ResponseEntity(new ApiResult(payload), status);
    }

    public ResponseEntity<ApiResult<T>> handleResponse(T payload) {
        return handleResponse(payload, HttpStatus.OK);
    }

    public ResponseEntity<ApiResult<T>> handleResponse(ApiResult<T> pagination) {
        return new ResponseEntity(pagination, HttpStatus.OK);
    }

}

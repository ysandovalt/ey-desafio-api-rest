package com.challenge.handler;

import com.challenge.exception.RequestValidationException;
import com.challenge.exception.ValidationException;
import com.challenge.payload.ApiResultError;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author ysand
 */
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handleGenericException(Exception ex) {
        return new ResponseEntity(new ApiResultError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, RequestValidationException.class, HttpRequestMethodNotSupportedException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResultError> handleNoReadeableException(Exception ex) {
        return new ResponseEntity(new ApiResultError("Formato solicitud o método incorrecto"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ApiResultError> handleInvalidEmailException(Exception ex) {
        return new ResponseEntity(new ApiResultError("Credenciales incorrectas"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ApiResultError> handleInvalidCredentialsException(Exception ex) {
        return new ResponseEntity(new ApiResultError("Credenciales incorrectas"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({DisabledException.class})
    public ResponseEntity<ApiResultError> handleDisableException(Exception ex) {
        return new ResponseEntity(new ApiResultError("Usuario no activo"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResultError> handleEntityNotFoundException(Exception ex) {
        return new ResponseEntity(new ApiResultError(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ApiResultError> handleValidationException(ValidationException ex) {
        return new ResponseEntity(new ApiResultError(ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }
}

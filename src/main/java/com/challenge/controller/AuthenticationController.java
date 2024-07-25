package com.challenge.controller;

import com.challenge.payload.ApiResult;
import com.challenge.payload.Login;
import com.challenge.payload.LoginRequest;
import com.challenge.service.IAuthService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ysand
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController extends BaseController{

    @Autowired
    IAuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResult<Login>> login(@Valid @RequestBody LoginRequest request) {
        return handleResponse(authService.authenticateUser(request));
    }
   
}

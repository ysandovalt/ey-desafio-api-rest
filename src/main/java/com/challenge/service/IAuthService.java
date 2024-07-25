package com.challenge.service;

import com.challenge.payload.Login;
import com.challenge.payload.LoginRequest;

/**
 *
 * @author ysand
 */
public interface IAuthService {
    
    Login authenticateUser(LoginRequest request);
}

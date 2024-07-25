package com.challenge.service.implement;

import com.challenge.payload.Login;
import com.challenge.payload.LoginRequest;
import com.challenge.service.IAuthService;
import com.challenge.service.IUserService;
import com.challenge.utility.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ysand
 */
@Service
@Slf4j
public class AuthServiceImpl implements IAuthService {

    @Autowired
    IUserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUserDetailsService userDetailsService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public Login authenticateUser(LoginRequest request) {
        Login login = new Login();
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));
        if (auth.isAuthenticated()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            login.setJwtToken(jwtTokenUtil.generateToken(userDetails));
            login.setEmail(request.getEmail());
            userService.register(login);
        } else {
            throw new BadCredentialsException("Invalid Credentials");
        }
        return login;
    }

}

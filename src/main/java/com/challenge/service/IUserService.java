package com.challenge.service;

import com.challenge.payload.Login;
import com.challenge.payload.UserCreateRequest;
import com.challenge.payload.UserCreateResponse;
import com.challenge.payload.UserListResponse;
import com.challenge.payload.UserUpdateRequest;
import java.util.List;

/**
 *
 * @author ysand
 */
public interface IUserService {

    UserCreateResponse create(UserCreateRequest request);

    UserCreateResponse modify(String correo, UserUpdateRequest request);

    void register(Login login);

    void delete(String correo);

    List<UserListResponse> getAll();

    List<UserListResponse> getOne(String correo);

}

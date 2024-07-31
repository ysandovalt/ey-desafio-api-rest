package com.challenge.controller;

import com.challenge.payload.*;
import com.challenge.service.IUserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ysand
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    @Autowired
    IUserService userService;

    /**
     *
     * @param request
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ADMIN"})
    public ResponseEntity<ApiResult<UserCreateResponse>> create(@Valid @RequestBody UserCreateRequest request) {
        UserCreateResponse data = userService.create(request);
        return handleResponse(data, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ADMIN"})
    public ResponseEntity<ApiResult<List<UserListResponse>>> getAll() {
        List<UserListResponse> usuarios = userService.getAll();
        return handleResponse(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ADMIN"})
    public ResponseEntity<ApiResult<List<UserListResponse>>> getOne(@PathVariable("id") String email) {
        List<UserListResponse> usuarios = userService.getOne(email);
        return handleResponse(usuarios, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ADMIN"})
    public ResponseEntity<ApiResult<UserCreateResponse>> modify(@PathVariable("id") String email, @RequestBody UserUpdateRequest request) {
        return handleResponse(userService.modify(email, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ADMIN"})
    public ResponseEntity delete(@PathVariable("id") String email) {
        userService.delete(email);
        return handleResponse(HttpStatus.OK);
    }
}

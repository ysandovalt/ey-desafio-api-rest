package com.challenge.controller;

import com.challenge.payload.ApiResult;
import com.challenge.payload.PhoneCreateRequest;
import com.challenge.payload.UserCreateRequest;
import com.challenge.payload.UserCreateResponse;
import com.challenge.payload.UserListResponse;
import com.challenge.payload.UserUpdateRequest;
import com.challenge.service.IUserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author ysand
 */
public class UserControllerTest {
    
    @Mock
    private IUserService userService;

    @InjectMocks
    private UserController instance = new UserController();
    private AutoCloseable mockito;
    private UserCreateResponse usuarioResponse;
    private UserListResponse usuariosResponse;
    private List<UserListResponse> usuarioList;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioResponse = new UserCreateResponse();
        usuarioList = Arrays.asList(usuariosResponse);

        Mockito.when(userService.getAll()).thenReturn(Arrays.asList(usuariosResponse));
        Mockito.when(userService.create(Mockito.any())).thenReturn(usuarioResponse);
    }
    
    @AfterEach
    public void tearDown() throws Exception {

    }

    /**
     * Test of create method, of class UserController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        PhoneCreateRequest phone = new PhoneCreateRequest("56", "02", "12345678");
        List<PhoneCreateRequest> phoneList = Arrays.asList(phone);
        
        UserCreateRequest request = new UserCreateRequest();
        request.setEmail("ysandovalt@gmail.com");
        request.setName("Yolanda Sandoval");
        request.setPassword("12345");
        request.setPhones(phoneList);
        
        ResponseEntity<ApiResult<UserCreateResponse>> expResult = new ResponseEntity<>(new ApiResult<>(usuarioResponse), HttpStatus.CREATED);
        ResponseEntity<ApiResult<UserCreateResponse>> result = instance.create(request);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAll method, of class UserController.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        ResponseEntity<ApiResult<List<UserListResponse>>> expResult =  new ResponseEntity<>(new ApiResult<>(usuarioList), HttpStatus.OK);
        ResponseEntity<ApiResult<List<UserListResponse>>> result = instance.getAll();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOne method, of class UserController.
     */
    public void testGetOne() {
        System.out.println("getOne");
        String email = "";

        ResponseEntity<ApiResult<List<UserListResponse>>> expResult = null;
        ResponseEntity<ApiResult<List<UserListResponse>>> result = instance.getOne(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modify method, of class UserController.
     */
    public void testModify() {
        System.out.println("modify");
        String email = "";
        UserUpdateRequest request = null;
        UserController instance = new UserController();
        ResponseEntity<ApiResult<UserCreateResponse>> expResult = null;
        ResponseEntity<ApiResult<UserCreateResponse>> result = instance.modify(email, request);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class UserController.
     */
    public void testDelete() {
        System.out.println("delete");
        String email = "";

        ResponseEntity expResult = null;
        ResponseEntity result = instance.delete(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

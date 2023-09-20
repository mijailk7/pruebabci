package com.prueba.bcidemo.service;

import com.prueba.bcidemo.model.request.UserLoginRequest;
import com.prueba.bcidemo.model.request.UserRequest;
import com.prueba.bcidemo.model.response.ApiResponseUser;
import java.util.Map;

public interface UserService {

    ApiResponseUser createUser(UserRequest userRepository);

    Map<String,String> loginUser(UserLoginRequest userRequest);
}

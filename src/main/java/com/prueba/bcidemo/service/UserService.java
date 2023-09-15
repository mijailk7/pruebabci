package com.prueba.bcidemo.service;

import com.prueba.bcidemo.model.request.UserRequest;
import com.prueba.bcidemo.model.response.ApiResponseUser;

public interface UserService {

    ApiResponseUser createUser(UserRequest userRepository);
}

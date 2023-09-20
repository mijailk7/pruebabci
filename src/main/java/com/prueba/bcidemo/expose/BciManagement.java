package com.prueba.bcidemo.expose;


import com.prueba.bcidemo.model.request.UserLoginRequest;
import com.prueba.bcidemo.model.response.ApiResponseUser;
import com.prueba.bcidemo.model.request.UserRequest;
import com.prueba.bcidemo.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BciManagement {
    @Autowired
    UserService userService;

@PostMapping("/create")
@ResponseStatus(HttpStatus.CREATED)
    public ApiResponseUser createUser(@RequestBody UserRequest userRequest){

    return userService.createUser(userRequest);
}

@PostMapping("/login")
@ResponseStatus(HttpStatus.OK)
@ResponseBody()
    public Map<String, String> login(@RequestBody UserLoginRequest userRequest){

    return userService.loginUser(userRequest);
}

}

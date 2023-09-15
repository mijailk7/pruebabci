package com.prueba.bcidemo.expose;


import com.prueba.bcidemo.model.response.ApiResponseUser;
import com.prueba.bcidemo.model.request.UserRequest;
import com.prueba.bcidemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bci/api")
@CrossOrigin
public class BciManagement {
    @Autowired
    UserService userService;

@PostMapping("/create")
@ResponseStatus(HttpStatus.CREATED)
    public ApiResponseUser createUser(@RequestBody UserRequest userRequest){

    return userService.createUser(userRequest);
}

}

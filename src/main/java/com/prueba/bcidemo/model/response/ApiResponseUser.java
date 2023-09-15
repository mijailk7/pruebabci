package com.prueba.bcidemo.model.response;

import com.prueba.bcidemo.model.request.UserRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseUser extends ApiResponse<UserRequest> {
    public ApiResponseUser(UserRequest data){
        super(data);
    }
}

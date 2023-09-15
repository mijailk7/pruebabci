package com.prueba.bcidemo.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private String message;
    private String code;
    private ResponseData responseData;

    public ApiResponse(T data){
        this.responseData = new ResponseData(data);
    }

}

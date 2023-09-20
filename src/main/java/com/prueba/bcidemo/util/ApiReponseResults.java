package com.prueba.bcidemo.util;

import com.prueba.bcidemo.model.response.ApiResponseUser;

public class ApiReponseResults {

    public ApiResponseUser setErrorMessage(ApiResponseUser apiResponseUser,String message){
        if(apiResponseUser == null){
            apiResponseUser = new ApiResponseUser(null);
        }
        apiResponseUser.setCode("400");
        apiResponseUser.setMessage(message);
        apiResponseUser.setResponseData(null);

        return apiResponseUser;
    }

    public ApiResponseUser setSuccesMessage(ApiResponseUser apiResponseUser,String message){
        apiResponseUser.setCode("200");
        apiResponseUser.setMessage(message);

        return apiResponseUser;
    }
}

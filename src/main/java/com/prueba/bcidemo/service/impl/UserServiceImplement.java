package com.prueba.bcidemo.service.impl;

import com.prueba.bcidemo.model.entity.Users;
import com.prueba.bcidemo.model.request.UserRequest;
import com.prueba.bcidemo.model.response.ApiResponseUser;
import com.prueba.bcidemo.repository.UserRepository;
import com.prueba.bcidemo.service.UserService;
import com.prueba.bcidemo.util.ApiReponseResults;
import com.prueba.bcidemo.util.DataAdapter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DataAdapter dataAdapter;


    private final ApiReponseResults apiReponseResults = new ApiReponseResults();
    private final List<Users> usersList = new ArrayList<>();

    @Override
    public ApiResponseUser createUser(UserRequest userRequest) {
        try {
            dataAdapter.convertUserReqToData(userRequest, usersList);
            ApiResponseUser apiResponseUser = new ApiResponseUser(userRequest);

            if (usersList.size() > 0) {
                Users objUser = usersList.get(0);
                userRepository.save(objUser);
                userRequest.setToken(objUser.getToken());
                apiResponseUser = new ApiResponseUser(userRequest);
                return apiReponseResults.setSuccesMessage(apiResponseUser, "Se registro con exito");
            } else
                return apiReponseResults.setErrorMessage(apiResponseUser,
                    "Bad Request No se encontro un telefono valido");
        }catch (Exception se){

            return apiReponseResults.setErrorMessage(null,se.getMessage());
        }
    }
}

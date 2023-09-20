package com.prueba.bcidemo.service.impl;

import com.prueba.bcidemo.model.entity.Users;
import com.prueba.bcidemo.model.request.UserLoginRequest;
import com.prueba.bcidemo.model.request.UserRequest;
import com.prueba.bcidemo.model.response.ApiResponseUser;
import com.prueba.bcidemo.repository.UserRepository;
import com.prueba.bcidemo.security.TokenUtil;
import com.prueba.bcidemo.service.UserService;
import com.prueba.bcidemo.util.ApiReponseResults;
import com.prueba.bcidemo.util.DataAdapter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DataAdapter dataAdapter;

    @Autowired
    private TokenUtil tokenUtil;

    private final ApiReponseResults apiReponseResults = new ApiReponseResults();

    @Override
    public ApiResponseUser createUser(UserRequest userRequest) {
        try {
            ApiResponseUser apiResponseUser = new ApiResponseUser(userRequest);
            boolean isRegister = userRepository.findByEmail(userRequest.getEmail()).isPresent();
            if(isRegister){
                return apiReponseResults.setErrorMessage(apiResponseUser,"El correo ya registrado");
            }
            Users objUser = dataAdapter.convertUserReqToData(userRequest);


            if (objUser != null) {
                userRepository.save(objUser);
                userRequest.setToken(objUser.getToken());
                userRequest.setUsername(objUser.getUsername());
                apiResponseUser = new ApiResponseUser(userRequest);
                return apiReponseResults.setSuccesMessage(apiResponseUser, "Se registro con exito");
            } else
                return apiReponseResults.setErrorMessage(apiResponseUser,
                    "No se encontro un telefono o email valido");
        }catch (Exception se){

            return apiReponseResults.setErrorMessage(null,se.getMessage());
        }
    }

    @Override
    public Map<String,String> loginUser(UserLoginRequest userRequest) {

        Map<String,String> httpResponse = new HashMap<>();

        try {
            Users user = userRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario " + userRequest.getUsername() + " no existe! " +
                    "exists!")
                );
            String token = "";

            if(dataAdapter.validateNullorEmptyString(user.getIsactive()) &&
                !user.getIsactive().equalsIgnoreCase("1")){
                httpResponse.put("Status","error");
                httpResponse.put("Message","Usuario no activo");
                return httpResponse;
            }
            if(dataAdapter.isPasswordValid(user.getPassword(),userRequest.getPassword())){
                if(!dataAdapter.validateNullorEmptyString(user.getToken()) || !tokenUtil.isValidToken(user.getToken())){
                    token = tokenUtil.createToken(userRequest.getUsername());
                }else{
                    token = user.getToken();
                }

                user.setToken(token);
                user.setLast_login(Calendar.getInstance().getTime());

                userRepository.save(user);

                httpResponse.put("Status","OK");
                httpResponse.put("token",token);
                httpResponse.put("Message","Login Correct");
                httpResponse.put("Username", userRequest.getUsername());
            }
            else {
                httpResponse.put("Status","error");
                httpResponse.put("Message","Login Incorrect");
            }
        }catch (UsernameNotFoundException e){
            httpResponse.put("Status","error");
            httpResponse.put("Message","User doesn't exists");
        }


        return httpResponse;
    }
}

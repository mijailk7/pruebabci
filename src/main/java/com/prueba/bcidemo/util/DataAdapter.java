package com.prueba.bcidemo.util;

import com.prueba.bcidemo.model.entity.Phones;
import com.prueba.bcidemo.model.entity.Users;
import com.prueba.bcidemo.model.request.UserRequest;
import com.prueba.bcidemo.security.TokenUtil;
import java.util.UUID;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DataAdapter {

    @Autowired
    TokenUtil tokenUtil;
    public Users convertUserReqToData(UserRequest userRequest){
        String userId = UUID.randomUUID().toString();
        String password = new BCryptPasswordEncoder().encode(userRequest.getPassword());
        Users objUser = new Users(userId,userRequest.getName(),userRequest.getEmail(),password
            );

        if(validateData(userRequest)){
            userRequest.phones.stream().forEach(p -> {
                Phones objPhones = new Phones(p.getNumber(),p.getCitycode());
                objUser.phones.add(objPhones);
            });
            return objUser;
        }
        return null;

    }

    public Boolean isPasswordValid(String encodePass,String password){
        BCryptPasswordEncoder passwordDecode = new BCryptPasswordEncoder();
        return passwordDecode.matches(password,encodePass);
    }

    public boolean validateData(UserRequest userRequest){
        boolean[] valid = {true};

        if(!patternMatches(userRequest.getEmail(),"^(.+)@(.+)$")){
            return valid[0] = false;
        }

        userRequest.phones.stream().forEach(p -> {
            if( (!validateNullorEmptyString(p.getNumber()) || !validateNullorEmptyString(p.citycode))
            || !patternMatches(p.getNumber(),"^[1-9](?!.*00)\\d{6}$"))
                valid[0] = false;
        });
        return valid[0];
    }

    public boolean validateNullorEmptyString(String validate){
        if(validate != null && !validate.isEmpty())
            return true;
        else
            return false;
    }
    public boolean patternMatches(String text, String regexPattern) {
        return Pattern.compile(regexPattern)
            .matcher(text)
            .matches();
    }
}

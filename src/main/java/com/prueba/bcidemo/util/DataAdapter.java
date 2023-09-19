package com.prueba.bcidemo.util;

import com.prueba.bcidemo.model.entity.Phones;
import com.prueba.bcidemo.model.entity.Users;
import com.prueba.bcidemo.model.request.UserRequest;
import com.prueba.bcidemo.security.TokenUtil;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataAdapter {

    @Autowired
    TokenUtil tokenUtil;
    public void convertUserReqToData(UserRequest userRequest, List<Users> users){
        String userId = UUID.randomUUID().toString();
        Users objUser = new Users(userId,userRequest.getName(),userRequest.getEmail(),
            userRequest.password,tokenUtil.createToken(userRequest.getName(),userId));

        if(validateData(userRequest)){
            userRequest.phones.stream().forEach(p -> {
                Phones objPhones = new Phones(p.getNumber(),p.getCitycode());
                objUser.phones.add(objPhones);
            });
            users.add(objUser);
        }

    }

    public boolean validateData(UserRequest userRequest){
        boolean[] valid = {true};
        userRequest.phones.stream().forEach(p -> {
            if( !validateNullorEmptyString(p.getNumber()) || !validateNullorEmptyString(p.citycode))
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
}

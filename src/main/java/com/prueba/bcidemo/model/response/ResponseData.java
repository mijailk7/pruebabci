package com.prueba.bcidemo.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData {

    private Object responseData;

    public ResponseData(Object data){
        this.responseData = data;
    }
}

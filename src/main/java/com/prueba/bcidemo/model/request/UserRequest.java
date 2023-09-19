package com.prueba.bcidemo.model.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {

    @JsonProperty("name")
    public String name;
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String password;
    public String token;
    @JsonProperty("phones")
    public List<PhoneRequest> phones;

}
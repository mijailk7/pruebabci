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
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String token;
    private String username;
    @JsonProperty("phones")
    public List<PhoneRequest> phones;

}
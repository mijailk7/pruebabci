package com.prueba.bcidemo.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
public class PhoneRequest {

    @JsonProperty("number")
    public String number;
    @JsonProperty("citycode")
    public String citycode;

}
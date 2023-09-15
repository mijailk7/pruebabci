package com.prueba.bcidemo.model.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Phones")
public class Phones {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String number;
    private String citycode;
    //public String users_id;

    public Phones(String number, String citycode){
        this.number = number;
        this.citycode = citycode;
    }
}

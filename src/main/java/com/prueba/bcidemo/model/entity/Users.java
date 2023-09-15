package com.prueba.bcidemo.model.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Users")
public class Users {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "users_unique_id")
    private String users_id;
    private String name;
    private String email;
    private String password;
    private Date created = new Date();
    private Date modified;
    private Date last_login;
    private String token;
    private String isactive;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name="users_id")
    public List<Phones> phones = new ArrayList<>();

    public Users(String user_id, String name, String email, String password) {
        this.users_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isactive = "1";
    }
}

package com.prueba.bcidemo.repository;

import com.prueba.bcidemo.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}

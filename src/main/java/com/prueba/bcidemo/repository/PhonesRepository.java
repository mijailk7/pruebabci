package com.prueba.bcidemo.repository;

import com.prueba.bcidemo.model.entity.Phones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhonesRepository extends JpaRepository<Phones,Long> {
}

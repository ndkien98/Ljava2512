package com.t3h.masterdataservice.repository;

import com.t3h.masterdataservice.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    List<Color> findByDeletedOrderByIdAsc(Byte deleted);
}

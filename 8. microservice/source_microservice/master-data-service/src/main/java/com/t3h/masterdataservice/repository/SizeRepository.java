package com.t3h.masterdataservice.repository;

import com.t3h.masterdataservice.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    List<Size> findByDeletedOrderByIdAsc(Byte deleted);
}

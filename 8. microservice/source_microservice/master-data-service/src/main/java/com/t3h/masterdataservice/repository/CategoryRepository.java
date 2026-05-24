package com.t3h.masterdataservice.repository;

import com.t3h.masterdataservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.parentId IS NULL AND c.deleted = 0")
    List<Category> findRootCategories();
}

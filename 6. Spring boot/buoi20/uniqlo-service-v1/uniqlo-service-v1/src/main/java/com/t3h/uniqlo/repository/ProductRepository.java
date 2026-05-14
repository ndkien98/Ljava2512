package com.t3h.uniqlo.repository;

import com.t3h.uniqlo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    @Query("select p from Product p where (:keyword is null or p.name like %:keyword%) and (:categoryId is null or p.category.id = :categoryId) and (:createdBy is null or p.createdBy = :createdBy) and p.deleted <> 1")
    Page<Product> getAllProducts(@Param("keyword") String keyword,
                                 @Param("categoryId") Integer categoryId,
                                @Param("createdBy") String createdBy
                                ,Pageable pageable);



    @Query(value = "select * from products p where (:keyword is null or p.name like %:keyword%) and (:categoryId is null or p.category_id = :categoryId) and (:createdBy is null or p.created_by = :createdBy) and p.deleted <> 1",nativeQuery = true)
    Page<Product> getAllProductsNativeQuery(@Param("keyword") String keyword,
                                 @Param("categoryId") Integer categoryId,
                                 @Param("createdBy") String createdBy
            ,Pageable pageable);
}


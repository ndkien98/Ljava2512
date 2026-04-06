package vn.edu.t3h.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Product {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String description;
    private String materialInfo;
    private String avatar;
    private Timestamp createdAt;
    private Integer createdBy;
    private Timestamp updatedAt;
    private Integer updatedBy;

    // Additional fields for display
    private String categoryName;
}

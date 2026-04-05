package vn.edu.t3h.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Category {
    private Integer id;
    private String name;
    private Integer parentId;
    private Timestamp createdAt;
    private Integer createdBy;
    private Timestamp updatedAt;
    private Integer updatedBy;
}

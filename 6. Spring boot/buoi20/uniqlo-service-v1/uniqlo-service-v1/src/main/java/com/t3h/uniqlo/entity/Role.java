package com.t3h.uniqlo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity{

    private String name;

}

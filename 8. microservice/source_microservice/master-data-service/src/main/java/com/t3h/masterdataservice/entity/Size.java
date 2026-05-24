package com.t3h.masterdataservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sizes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Size extends BaseEntity {

    @Column(name = "size_code", nullable = false, length = 20)
    private String sizeCode;
}

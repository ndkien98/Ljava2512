package com.t3h.uniqlo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "sizes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Size extends BaseEntity{

    @Column(name = "size_code", nullable = false, length = 20)
    private String sizeCode;

}

package com.t3h.masterdataservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "colors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Color extends BaseEntity {

    @Column(name = "color_code", nullable = false, length = 50)
    private String colorCode;

    @Column(name = "hex_code", length = 10)
    private String hexCode;
}

package com.t3h.uniqlo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "visit_stats")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitStat extends BaseEntity{

    @Column(name = "visit_count", nullable = false)
    @Builder.Default
    private Long visitCount = 0L;

}

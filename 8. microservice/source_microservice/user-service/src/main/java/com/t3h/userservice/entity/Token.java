package com.t3h.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token extends BaseEntity {

    @Column(unique = true, columnDefinition = "TEXT")
    private String token;

    @Column(name = "token_type")
    private String tokenType = "BEARER";

    private boolean revoked;
    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

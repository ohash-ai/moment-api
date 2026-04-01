package com.moment.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "moment_like", uniqueConstraints = {
        @UniqueConstraint(name = "uk_moment_user", columnNames = {"moment_id", "user_id"})
})
public class MomentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "moment_id", nullable = false)
    private Long momentId;

    @Column(name = "user_id", length = 100, nullable = false)
    private String userId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}

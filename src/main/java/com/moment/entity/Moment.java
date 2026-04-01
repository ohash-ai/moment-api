package com.moment.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "moment")
public class Moment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String date;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(length = 500)
    private String quote;

    @Column(length = 200)
    private String source;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_featured")
    private Boolean isFeatured = false;

    @Column(name = "like_count")
    private Integer likeCount = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}

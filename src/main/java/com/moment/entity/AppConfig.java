package com.moment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "app_config")
public class AppConfig {

    @Id
    @Column(name = "config_key", length = 100)
    private String configKey;

    @Column(name = "config_value", columnDefinition = "TEXT")
    private String configValue;
}

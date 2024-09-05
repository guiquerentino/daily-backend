package br.com.daily.backend.modules.goals.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "GOALS_INFO")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String title;

    private String createdBy;

    private LocalDateTime creationDate = LocalDateTime.now();

    private boolean isDone;

    private boolean isAllDay;

    private LocalDateTime scheduledTime;
}

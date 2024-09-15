package br.com.daily.backend.modules.goals.domain;

import br.com.daily.backend.modules.goals.domain.dto.GoalRecord;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "created_by")
    private String createdBy;

    @Column(nullable = false, name = "is_completed")
    private boolean isCompleted;

    @Column(nullable = false, name = "is_all_day")
    private boolean isAllDay;

    @Column(nullable = true, name = "scheduled_time")
    private LocalDateTime scheduledTime;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true, updatable = true)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true, updatable = true)
    private LocalDateTime deletedAt;

    @PrePersist
    private void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static GoalRecord mapToRecord(Goal goal){
        return new GoalRecord(goal.getId(), goal.getUserId(), goal.getTitle(), goal.getCreatedBy(), goal.getCreatedAt(), goal.getScheduledTime(), goal.isAllDay(),goal.isCompleted());
    }

}

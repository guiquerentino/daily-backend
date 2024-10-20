package br.com.daily.backend.modules.accounts.domain;

import br.com.daily.backend.modules.accounts.domain.dto.UserRecord;
import br.com.daily.backend.modules.accounts.domain.enums.ROLE;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ROLE role;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password", nullable = false)
    private byte[] password;

    @Column(name = "password_salt", nullable = false)
    private byte[] passwordSalt;

    @Column(name = "has_onboarding", nullable = false)
    private boolean hasOnboarding;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

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

    public static UserRecord mapToRecord(User user) {
        return new UserRecord(
                user.getId(),
                user.getRole(),
                user.getEmail(),
                user.isHasOnboarding()
        );
    }

}

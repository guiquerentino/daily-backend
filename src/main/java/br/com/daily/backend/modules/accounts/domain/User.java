package br.com.daily.backend.modules.accounts.domain;

import br.com.daily.backend.modules.accounts.domain.enums.GENDER;
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

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "gender", nullable = true)
    private GENDER gender;

    @Column(name = "profile_photo", nullable = true)
    private byte[] profilePhoto;

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

}

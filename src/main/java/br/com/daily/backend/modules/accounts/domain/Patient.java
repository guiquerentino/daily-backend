package br.com.daily.backend.modules.accounts.domain;

import br.com.daily.backend.modules.accounts.domain.enums.GENDER;
import br.com.daily.backend.modules.accounts.domain.enums.MEDITATION_EXPERIENCE;
import br.com.daily.backend.modules.accounts.domain.enums.TARGET;
import jakarta.persistence.*;
import lombok.Data;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    private Psychologist psychologist;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "gender", nullable = true)
    private GENDER gender;

    @Column(name = "age", nullable = true)
    private int age;

    @Column(name = "profile_photo", nullable = true)
    private byte[] profilePhoto;

    @Column(name = "target")
    @ElementCollection(targetClass = TARGET.class)
    private List<TARGET> targets = new ArrayList<>();

    @Column(name = "meditation_experience", nullable = true)
    @Enumerated(EnumType.STRING)
    private MEDITATION_EXPERIENCE meditationExperience;

    @Column(name = "connection_code", nullable = false, unique = true, length = 6)
    private String connectionCode;


    @PrePersist
    private void prePersist() {
        connectionCode = generateRandomCode();
    }

    private String generateRandomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

}

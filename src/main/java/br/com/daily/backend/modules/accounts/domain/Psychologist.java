package br.com.daily.backend.modules.accounts.domain;

import br.com.daily.backend.modules.accounts.domain.enums.GENDER;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "psychologists")
public class Psychologist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "gender", nullable = false)
    private GENDER gender;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "profile_photo", nullable = false)
    private byte[] profilePhoto;

    @OneToMany(mappedBy = "psychologist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patients = new ArrayList<>();
    
}

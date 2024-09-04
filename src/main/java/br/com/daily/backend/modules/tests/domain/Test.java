package br.com.daily.backend.modules.tests.domain;

import br.com.daily.backend.core.utils.QuestionsConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "TESTS_INFO")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String text;

    private String bannerUrl;

    @Column(columnDefinition = "TEXT")
    private String questions;

}

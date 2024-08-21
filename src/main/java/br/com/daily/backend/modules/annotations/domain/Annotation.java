package br.com.daily.backend.modules.annotations.domain;

import br.com.daily.backend.modules.annotations.domain.dto.AnnotationDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "ANNOTATIONS_INFO")
public class Annotation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long authorId;
    private String text;
    private LocalDateTime creationDate = LocalDateTime.now();

public static AnnotationDTO mapToDTO(Annotation annotation) {
    AnnotationDTO annotationDTO = new AnnotationDTO();

    annotationDTO.setAuthorId(annotationDTO.getAuthorId());
    annotationDTO.setText(annotation.getText());
    annotationDTO.setCreationDate(annotation.getCreationDate());

    return annotationDTO;
}
}

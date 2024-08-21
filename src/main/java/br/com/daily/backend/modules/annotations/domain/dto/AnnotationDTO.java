package br.com.daily.backend.modules.annotations.domain.dto;

import br.com.daily.backend.modules.annotations.domain.Annotation;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AnnotationDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long authorId;
    private String text;
    private LocalDateTime creationDate = LocalDateTime.now();

    public Annotation mapToDO(AnnotationDTO annotationDTO){
        Annotation annotation = new Annotation();

        annotation.setAuthorId(annotationDTO.getAuthorId());
        annotation.setText(annotationDTO.getText());
        annotation.setCreationDate(annotationDTO.getCreationDate());

        return annotation;
    }

}

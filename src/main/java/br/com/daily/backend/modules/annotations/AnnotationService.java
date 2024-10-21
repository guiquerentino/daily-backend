package br.com.daily.backend.modules.annotations;

import br.com.daily.backend.core.exceptions.GenericException;
import br.com.daily.backend.modules.annotations.domain.Annotation;
import br.com.daily.backend.modules.annotations.domain.dto.AnnotationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnnotationService {

    @Autowired
    AnnotationRepository repository;

    public AnnotationRecord createAnnotation(AnnotationRecord annotation) {
        Annotation dbAnnotation = new Annotation();

        dbAnnotation.setText(annotation.text());
        dbAnnotation.setUserId(annotation.userId());

        return Annotation.mapToRecord(repository.save(dbAnnotation));
    }

    public List<AnnotationRecord> listAllByAuthorId(Long userId) {

        List<Annotation> annotationList = repository.findByUserId(userId);
        List<AnnotationRecord> annotationRecords = new ArrayList<>();

        for (Annotation item : annotationList) {
            AnnotationRecord record = Annotation.mapToRecord(item);
            annotationRecords.add(record);
        }

        return annotationRecords;
    }

    public AnnotationRecord updateAnnotation(AnnotationRecord annotation) {
        Optional<Annotation> optionalAnnotation = repository.findById(annotation.id());

        if (optionalAnnotation.isPresent()) {
            Annotation annotationDB = optionalAnnotation.get();
            annotationDB.setText(annotation.text());

            return Annotation.mapToRecord(repository.save(annotationDB));
        }

        throw new GenericException("ANNOTATION_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    public void deleteAnnotation(Long annotationId) {
        Optional<Annotation> optionalAnnotation = repository.findById(annotationId);

        if (optionalAnnotation.isPresent()) {
            repository.delete(optionalAnnotation.get());
        } else {
            throw new GenericException("ANNOTATION_NOT_FOUND", HttpStatus.NOT_FOUND);
        }

    }

}

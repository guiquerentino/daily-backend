package br.com.daily.backend.modules.annotations;

import br.com.daily.backend.modules.annotations.domain.Annotation;
import br.com.daily.backend.modules.annotations.domain.dto.AnnotationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/annotations")
public class AnnotationController {

    @Autowired
    AnnotationService service;

    @Autowired
    AnnotationRepository repository;

    @GetMapping
    public ResponseEntity<List<AnnotationRecord>> listAll(@RequestParam Long authorId){
        return new ResponseEntity<>(service.listAllByAuthorId(authorId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnnotationRecord> createAnnotation(@RequestBody AnnotationRecord request){
        return new ResponseEntity<>(service.createAnnotation(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AnnotationRecord> updateAnnotation(@RequestBody AnnotationRecord request){
        return new ResponseEntity<>(service.updateAnnotation(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAnnotation(@RequestParam Long id){
        service.deleteAnnotation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

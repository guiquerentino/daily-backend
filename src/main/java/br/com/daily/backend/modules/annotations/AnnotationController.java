package br.com.daily.backend.modules.annotations;

import br.com.daily.backend.modules.annotations.domain.Annotation;
import br.com.daily.backend.modules.annotations.domain.dto.AnnotationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/v1/annotations")
public class AnnotationController {
    @Autowired
    AnnotationService service;

    @Autowired
    AnnotationRepository repository;

    @GetMapping
    public ResponseEntity<Object> listAll(@RequestParam Long authorId){
        return new ResponseEntity<>(service.listAllByAuthorId(authorId),HttpStatus.OK);
    }

    @PostMapping
    public Annotation createAnnotation(@RequestBody Annotation request){
        return repository.save(request);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAnnotation(@RequestParam Long id){
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

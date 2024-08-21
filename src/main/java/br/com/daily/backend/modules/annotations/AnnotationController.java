package br.com.daily.backend.modules.annotations;

import br.com.daily.backend.modules.annotations.domain.Annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/v1/annotation")
public class AnnotationController {
    @Autowired
    AnnotationService service;

    @GetMapping
    public ResponseEntity<Object> listAll(@RequestParam Long authorId){
        return new ResponseEntity<>(service.listAllByAuthorId(authorId),HttpStatus.OK);
    }

}

package br.com.daily.backend.modules.tags;

import br.com.daily.backend.modules.tags.domain.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tags")
public class TagsController {

    @Autowired
    private TagsService service;

    @GetMapping
    private ResponseEntity<List<TagDTO>> returnAllTags(){
        return new ResponseEntity<>(service.returnAllTags(), HttpStatus.OK);
    }

}

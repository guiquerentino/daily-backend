package br.com.daily.backend.modules.tests;

import br.com.daily.backend.modules.tests.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
public class TestsController {

    @Autowired
    TestsRepository repository;

    @GetMapping
    public List<Test> fetchAllTests(){
        return repository.findAll();
    }

    @PostMapping
    public Test saveTest(@RequestBody Test request){
        return repository.save(request);
    }

}

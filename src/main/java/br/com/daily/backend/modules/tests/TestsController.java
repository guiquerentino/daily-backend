package br.com.daily.backend.modules.tests;

import br.com.daily.backend.modules.tests.domain.Test;
import br.com.daily.backend.modules.tests.domain.dto.TestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
public class TestsController {

    @Autowired
    TestsService service;

    @PostMapping
    public ResponseEntity<TestRecord> createTest(@RequestBody TestRecord test) {
        return new ResponseEntity<>(service.createTest(test), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TestRecord>> getTests() {
        return new ResponseEntity<>(service.fetchAllTests(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TestRecord> updateTest(@RequestBody TestRecord test) {
        return new ResponseEntity<>(service.updateTest(test), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<TestRecord> deleteTest(@RequestParam Long id) {
        service.deleteTest(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

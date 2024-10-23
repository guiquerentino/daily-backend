package br.com.daily.backend.modules.patients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/patients")
public class PatientsController {

    @Autowired
    PatientsService patientsService;

    @PostMapping
    public ResponseEntity<Object> connectUsersWithCode(@RequestParam String code, @RequestParam Long userId){
        patientsService.accountUsersWithCode(code, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

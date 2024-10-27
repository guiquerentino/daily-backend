package br.com.daily.backend.modules.patients;

import br.com.daily.backend.modules.accounts.domain.Patient;
import br.com.daily.backend.modules.accounts.domain.dto.PatientDTO;
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
    public ResponseEntity<PatientDTO> connectUsersWithCode(@RequestParam String code, @RequestParam Long userId){
        return new ResponseEntity<>(patientsService.connectUsersWithCode(code, userId), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Object> removeUsersConnection(@RequestParam Long id){
        patientsService.removeUsersConnection(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

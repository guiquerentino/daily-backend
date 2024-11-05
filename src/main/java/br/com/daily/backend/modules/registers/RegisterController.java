package br.com.daily.backend.modules.registers;

import br.com.daily.backend.core.exceptions.GenericException;
import br.com.daily.backend.modules.registers.domain.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/registers")
public class RegisterController {

    @Autowired
    RegisterRepository repository;

    @GetMapping
    public List<Register> getAllRegisterById(@RequestParam Long userId){
        return repository.findByUserId(userId);
    }

    @PostMapping
    public Register createRegister(@RequestBody Register register){
        return repository.save(register);
    }

    @PutMapping
    public Register updateRegister(@RequestBody Register register){
        Optional<Register> registerDb = repository.findById(register.getId());

        if(registerDb.isPresent()){
            Register presentRegister = registerDb.get();

            presentRegister.setText(register.getText());
            presentRegister.setPatientName(register.getPatientName());

            return repository.save(presentRegister);
        }

        throw new GenericException("Registro não encontrado", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public void deleteRegister(@RequestParam Long registerId){
        repository.deleteById(registerId);
    }

}

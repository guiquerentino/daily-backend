package br.com.daily.backend.modules.registers;

import br.com.daily.backend.modules.registers.domain.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/register")
public class RegisterController {

    @Autowired
    private RegisterRepository repository;

    @PostMapping
    private Register saveRegister(@RequestBody Register request) {

        request.setDataHoraCriacao(LocalDateTime.now());

        return repository.save(request);
    }

    @GetMapping("{id}")
    private Register returnUniqueRegister(@PathVariable Long id) throws Exception {

        Optional<Register> register = repository.findById(id);

        if (register.isPresent()) {
            return register.get();
        }

        throw new Exception("Erro ao recuperar registro");
    }

    @GetMapping("/user/{ownerId}")
    private List<Register> returnAllRegistersByOwner(@PathVariable Long ownerId, @RequestParam String dataHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataHoraObj = LocalDateTime.parse(dataHora, formatter);

        LocalDateTime proximoDia = dataHoraObj.plusDays(1);

        return repository.findByOwnerIdAndDataHoraCriacaoBetween(ownerId, dataHoraObj, proximoDia);
    }

    @PutMapping("{id}")
    private Register updateRegister(@PathVariable Long id, @RequestBody Register newRegister) throws Exception {

        Optional<Register> registerDB = repository.findById(id);

        if (registerDB.isPresent()) {
            Register register = registerDB.get();

            register.setText(newRegister.getText());
            register.setTags(newRegister.getTags());

            return repository.save(register);
        }

        throw new Exception("Erro ao recuperar registro");
    }

    @DeleteMapping("{id}")
    private void deleteRegister(@PathVariable Long id) {
        repository.deleteById(id);
    }

}

package br.com.daily.backend.modules.patients;

import br.com.daily.backend.core.exceptions.GenericException;
import br.com.daily.backend.modules.accounts.PatientRepository;
import br.com.daily.backend.modules.accounts.PsychologistRepository;
import br.com.daily.backend.modules.accounts.domain.Patient;
import br.com.daily.backend.modules.accounts.domain.Psychologist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientsService {

    @Autowired
    PatientRepository repository;

    @Autowired
    PsychologistRepository psychologistRepository;

    public void accountUsersWithCode(String code, Long userId){

        Optional<Patient> patient = repository.findByConnectionCode(code);

        if(patient.isPresent()){
            Psychologist psychologist = psychologistRepository.findByUserId(userId);
            psychologist.getPatients().add(patient.get());

            patient.get().setPsychologist(psychologist);

            psychologistRepository.save(psychologist);
            repository.save(patient.get());

            return;
        }

        throw new GenericException("Erro ao realizar a conexão.", HttpStatus.BAD_REQUEST);


    }

}

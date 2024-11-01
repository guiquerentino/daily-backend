package br.com.daily.backend.modules.patients;

import br.com.daily.backend.core.exceptions.GenericException;
import br.com.daily.backend.modules.accounts.PatientRepository;
import br.com.daily.backend.modules.accounts.PsychologistRepository;
import br.com.daily.backend.modules.accounts.domain.Patient;
import br.com.daily.backend.modules.accounts.domain.Psychologist;
import br.com.daily.backend.modules.accounts.domain.dto.PatientDTO;
import br.com.daily.backend.modules.chat.ChatRepository;
import br.com.daily.backend.modules.chat.models.Chat;
import br.com.daily.backend.modules.emotions.EmotionRepository;
import br.com.daily.backend.modules.emotions.domain.Emotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientsService {

    @Autowired
    PatientRepository repository;

    @Autowired
    EmotionRepository emotionRepository;

    @Autowired
    PsychologistRepository psychologistRepository;

    @Autowired
    ChatRepository chatRepository;

    public PatientDTO connectUsersWithCode(String code, Long userId){

        Optional<Patient> patient = repository.findByConnectionCode(code);

        if(patient.isPresent()){
            Psychologist psychologist = psychologistRepository.findByUserId(userId);
            psychologist.getPatients().add(patient.get());

            if(patient.get().getPsychologist() != null){
                throw new GenericException("Erro ao realizar a conexão.", HttpStatus.BAD_REQUEST);
            }

            patient.get().setPsychologist(psychologist.getId());

            psychologistRepository.save(psychologist);

            Optional<Emotion> lastEmotion = emotionRepository.findFirstByUserIdOrderByCreatedAtDesc(patient.get().getUser().getId());

            Chat chat = new Chat();

            chat.setPatientId(patient.get().getId().toString());
            chat.setPsychologistId(psychologist.getId().toString());

            chatRepository.save(chat);

            PatientDTO patientdto = PatientDTO.from(repository.save(patient.get()), lastEmotion.orElse(null));

            return patientdto;
        }

        throw new GenericException("Erro ao realizar a conexão.", HttpStatus.BAD_REQUEST);


    }

    public void removeUsersConnection(Long id){
        Optional<Patient> OptionalPatient = repository.findById(id);

        if(OptionalPatient.isPresent()){
            Patient patient = OptionalPatient.get();
            patient.setPsychologist(null);

            Chat chat = chatRepository.findByPatientId(patient.getId().toString());

            repository.save(patient);
            chatRepository.delete(chat);

        } else {
            throw new GenericException("Erro ao desconectar paciente.", HttpStatus.BAD_REQUEST);
        }

    }

}

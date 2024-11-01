package br.com.daily.backend.modules.accounts;

import br.com.daily.backend.modules.accounts.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByUserId(Long userId);
    Optional<Patient> findByConnectionCode(String connectionCode);
    List<Patient> findByPsychologist(Long psychologistId);


}

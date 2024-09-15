package br.com.daily.backend.modules.accounts;

import br.com.daily.backend.modules.accounts.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByUserId(Long userId);

}

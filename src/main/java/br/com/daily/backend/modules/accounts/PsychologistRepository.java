package br.com.daily.backend.modules.accounts;

import br.com.daily.backend.modules.accounts.domain.Patient;
import br.com.daily.backend.modules.accounts.domain.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {

    Psychologist findByUserId(Long userId);

}

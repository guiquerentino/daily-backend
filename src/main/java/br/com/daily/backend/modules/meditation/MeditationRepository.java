package br.com.daily.backend.modules.meditation;

import br.com.daily.backend.modules.meditation.domain.Meditation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeditationRepository extends JpaRepository<Meditation,Long> {
}

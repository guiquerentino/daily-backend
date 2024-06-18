package br.com.daily.backend.modules.emotions;

import br.com.daily.backend.modules.emotions.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {

    List<Emotion> findByOwnerIdAndCreationDateBetween(Long ownerId, LocalDateTime startDate, LocalDateTime endDate);

}

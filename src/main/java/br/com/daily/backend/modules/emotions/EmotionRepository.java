package br.com.daily.backend.modules.emotions;

import br.com.daily.backend.modules.emotions.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {

    List<Emotion> findByUserIdAndCreatedAtBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    List<Emotion> findByUserId(Long userId);
    Optional<Emotion> findFirstByUserIdOrderByCreatedAtDesc(Long userId);

}

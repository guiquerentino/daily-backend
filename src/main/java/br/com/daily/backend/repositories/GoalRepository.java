package br.com.daily.backend.repositories;

import br.com.daily.backend.entities.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, Long> {
}

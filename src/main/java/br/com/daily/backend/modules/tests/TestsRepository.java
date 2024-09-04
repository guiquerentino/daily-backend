package br.com.daily.backend.modules.tests;

import br.com.daily.backend.modules.tests.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestsRepository extends JpaRepository<Test,Long> {
}

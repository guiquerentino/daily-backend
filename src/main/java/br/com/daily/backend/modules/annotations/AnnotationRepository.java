package br.com.daily.backend.modules.annotations;

import br.com.daily.backend.modules.annotations.domain.Annotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnotationRepository extends JpaRepository<Annotation,Long> {
    List<Annotation> findByAuthorId(Long authorId);
}

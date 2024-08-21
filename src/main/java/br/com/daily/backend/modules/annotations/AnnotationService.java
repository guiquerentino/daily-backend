package br.com.daily.backend.modules.annotations;

import br.com.daily.backend.modules.annotations.domain.Annotation;
import br.com.daily.backend.modules.annotations.domain.dto.AnnotationDTO;
import br.com.daily.backend.modules.articles.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnotationService {
     @Autowired
    AnnotationRepository repository;

    public List<AnnotationDTO> listAllByAuthorId(Long authorId){
        List<Annotation> annotationList = repository.findByAuthorId(authorId);
        List<AnnotationDTO> annotationDTOList = new ArrayList<>();
        for (Annotation item: annotationList){
            AnnotationDTO annotationDTO = Annotation.mapToDTO(item);
            annotationDTOList.add(annotationDTO);
        }
        return annotationDTOList;
    }
}

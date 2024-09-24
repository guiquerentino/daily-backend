package br.com.daily.backend.modules.tags;

import br.com.daily.backend.modules.tags.domain.Tag;
import br.com.daily.backend.modules.tags.domain.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagsService {

    @Autowired
    private TagRepository tagRepository;

    public List<TagDTO> returnAllTags() {

        List<TagDTO> response = new ArrayList<>();
        tagRepository.findAll().forEach(tag -> response.add(Tag.mapToDTO(tag)));

        return response;
    }

}

package br.com.daily.backend.modules.tags.domain;

import br.com.daily.backend.modules.tags.domain.dto.TagDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String emote;
    private String text;

    public static TagDTO mapToDTO(Tag dObject) {
        TagDTO tag = new TagDTO();

        tag.setEmote(dObject.getEmote());
        tag.setId(dObject.getId());
        tag.setText(dObject.getText());

        return tag;
    }

}

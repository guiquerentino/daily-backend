package br.com.daily.backend.modules.tags.domain.dto;

import br.com.daily.backend.modules.tags.domain.Tag;
import lombok.Data;

@Data
public class TagDTO {

    private Long id;
    private String emote;
    private String text;

    public static Tag mapToDo(TagDTO dto) {
        Tag tag = new Tag();

        tag.setEmote(dto.getEmote());
        tag.setId(dto.getId());
        tag.setText(dto.getText());

        return tag;
    }

}

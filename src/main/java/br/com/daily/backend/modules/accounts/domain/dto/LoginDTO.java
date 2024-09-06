package br.com.daily.backend.modules.accounts.domain.dto;

import br.com.daily.backend.modules.accounts.domain.enums.ROLE;
import lombok.Data;

@Data
public class LoginDTO {

    private String email;
    private String password;
    private ROLE accountType;

}

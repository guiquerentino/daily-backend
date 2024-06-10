package br.com.daily.backend.modules.accounts.domain.dto;

import br.com.daily.backend.modules.accounts.domain.enums.ACCOUNT_TYPE;
import lombok.Data;

@Data
public class CreateAccountDTO {

    private String email;
    private String password;
    private String fullName;
    private ACCOUNT_TYPE accountType;

}

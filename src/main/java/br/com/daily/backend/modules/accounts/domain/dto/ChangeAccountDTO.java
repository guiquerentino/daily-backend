package br.com.daily.backend.modules.accounts.domain.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ChangeAccountDTO {

    private BigInteger userId;
    private String fullName;
    private String email;
    private int gender;

}

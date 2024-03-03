package br.com.daily.backend.entities;

import java.math.BigInteger;

import br.com.daily.backend.entities.enums.ACCOUNT_TYPE;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USER_INFO")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private ACCOUNT_TYPE accountType;
    private String email;
    private String password;
    private byte[] passwordSalt;
    private String fullName;
    private String age;
    private String phoneNumber;
    private String personalDocument;
    private String professionalDocument;
    private String hashAlgorithm;
    private boolean canAttend;
}

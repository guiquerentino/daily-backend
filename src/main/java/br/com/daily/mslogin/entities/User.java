package br.com.daily.mslogin.entities;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    
    @Id
    private BigInteger id;
    private String email;
    private String passwordHash;

}

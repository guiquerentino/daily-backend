package br.com.daily.backend.utils;

import br.com.daily.backend.entities.Account;
import br.com.daily.backend.entities.dtos.AccountDTO;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Component
public class AccountUtils {

    public Account hashPassword(AccountDTO account) {
        Account response = AccountDTO.mapToDO(account);

        response.setPasswordSalt(generatePasswordSalt());

        Argon2BytesGenerator generate = new Argon2BytesGenerator();
        generate.init(buildArgon2(response.getPasswordSalt()).build());
        byte[] result = new byte[32];
        generate.generateBytes(account.getPassword().getBytes(StandardCharsets.UTF_8), result, 0, result.length);

        response.setPassword(result);

        return response;
    }

    public byte[] hashPasswordToAuthorize(String password, byte[] salt) {

            Argon2BytesGenerator generate = new Argon2BytesGenerator();
        generate.init(buildArgon2(salt).build());
        byte[] result = new byte[32];
        generate.generateBytes(password.getBytes(StandardCharsets.UTF_8), result, 0, result.length);

        return result;
    }

    private byte[] generatePasswordSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        return salt;
    }

    private Argon2Parameters.Builder buildArgon2(byte[] salt) {
        return new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                .withIterations(1)
                .withMemoryAsKB(33536)
                .withParallelism(1)
                .withSalt(salt);
    }

}

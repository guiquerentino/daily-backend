package br.com.daily.backend.modules.accounts;

import br.com.daily.backend.modules.accounts.domain.Account;
import br.com.daily.backend.modules.accounts.domain.dto.ChangePasswordDTO;
import br.com.daily.backend.modules.accounts.domain.dto.CreateAccountDTO;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Component
public class AccountUtils {

    public Account hashPassword(CreateAccountDTO account) {
        Account response = new Account();
        response.setAccountType(account.getAccountType());
        response.setHashAlgorithm("Argon2Id");
        response.setFullName(account.getFullName());
        response.setEmail(account.getEmail());

        response.setPasswordSalt(generatePasswordSalt());

        Argon2BytesGenerator generate = new Argon2BytesGenerator();
        generate.init(buildArgon2(response.getPasswordSalt()).build());
        byte[] result = new byte[32];
        generate.generateBytes(account.getPassword().getBytes(StandardCharsets.UTF_8), result, 0, result.length);

        response.setPassword(result);

        return response;
    }

    public Account hashPassword(ChangePasswordDTO account) {
        Account response = new Account();
        response.setHashAlgorithm("Argon2Id");
        response.setEmail(account.getEmail());

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

package br.com.daily.backend.utils;

import br.com.daily.backend.entities.Account;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Component
public class AccountUtils {

    public Account hashPassword(Account account) {
        account.setHashAlgorithm("Argon2Id");

        account.setPasswordSalt(generatePasswordSalt());

        int iterations = 2;
        int memLimit = 66536;
        int hashLength = 32;
        int parallelism = 1;

        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                .withIterations(iterations)
                .withMemoryAsKB(memLimit)
                .withParallelism(parallelism)
                .withSalt(account.getPasswordSalt());

        Argon2BytesGenerator generate = new Argon2BytesGenerator();
        generate.init(builder.build());
        byte[] result = new byte[hashLength];
        generate.generateBytes(account.getPassword().getBytes(StandardCharsets.UTF_8), result, 0, result.length);

    }

    private byte[] generatePasswordSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        return salt;
    }

}

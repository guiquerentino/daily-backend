package br.com.daily.backend.modules.code;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String generateAccountCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

}

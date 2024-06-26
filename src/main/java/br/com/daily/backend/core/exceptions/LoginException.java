package br.com.daily.backend.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoginException extends RuntimeException {

    private final HttpStatus status;

    public LoginException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

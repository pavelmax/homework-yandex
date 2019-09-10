package ru.yandex.homework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFountException extends RuntimeException {
    private Date createAt = new Date();

    public NotFountException() {
        super();
    }
    public NotFountException(String msg) {
        super(msg);
    }
}

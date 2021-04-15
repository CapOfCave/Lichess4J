package me.kecker.lichess4j.http.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HttpException extends Exception {

    private static final long serialVersionUID = 1L;

    public HttpException(String message) {
        super(message);
    }

}

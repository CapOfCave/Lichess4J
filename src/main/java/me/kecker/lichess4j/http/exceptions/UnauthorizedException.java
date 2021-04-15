package me.kecker.lichess4j.http.exceptions;

public class UnauthorizedException extends IllegalStatusCodeException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
        this(401);
    }

    public UnauthorizedException(int statusCode) {
        super(statusCode);
    }

}

package me.kecker.lichess4j.http.exceptions;

public class IllegalStatusCodeException extends HttpException {

    private static final long serialVersionUID = 1L;

    public IllegalStatusCodeException(int statusCode) {
        super("Status code: " + statusCode);
    }

}

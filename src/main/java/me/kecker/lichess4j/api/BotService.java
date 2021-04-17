package me.kecker.lichess4j.api;

import java.io.IOException;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;

public interface BotService {

    void upgrade()
            throws IOException, InterruptedException, UnauthorizedException,
            IllegalStatusCodeException;

}

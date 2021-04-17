package me.kecker.lichess4j.services;

import java.io.IOException;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import me.kecker.lichess4j.api.BotService;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;

@AllArgsConstructor
public class BotHttpService implements BotService {
    private static final String ENDPOINT = "bot";

    @NonNull
    private HttpBaseClient httpBaseClient;

    @Override
    public void upgrade() throws IOException, InterruptedException, UnauthorizedException,
            IllegalStatusCodeException {
        this.httpBaseClient.post(ENDPOINT, "account/upgrade", Collections.emptyMap(),
                HttpBaseClient.NO_RESPONSE, BodyPublishers.noBody());
    }
}

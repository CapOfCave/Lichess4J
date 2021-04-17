package me.kecker.lichess4j.services;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import me.kecker.lichess4j.api.BoardService;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;

@AllArgsConstructor
public class BoardHttpService implements BoardService {

    private static final String ENDPOINT = "board";

    @NonNull
    private HttpBaseClient httpBaseClient;

    @Override
    public void abortGame(String gameId) throws IOException, InterruptedException,
            UnauthorizedException, IllegalStatusCodeException {

        String path = String.format("game/%s/abort", URLEncoder.encode(gameId,
                StandardCharsets.UTF_8));
        this.httpBaseClient.post(ENDPOINT, path, Collections.emptyMap(), HttpBaseClient.NO_RESPONSE,
                BodyPublishers.noBody());
    }
}

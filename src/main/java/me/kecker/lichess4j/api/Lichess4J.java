package me.kecker.lichess4j.api;

import java.net.http.HttpClient;
import lombok.NonNull;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.base.HttpRequestFactory;
import me.kecker.lichess4j.http.utils.GsonFactory;
import me.kecker.lichess4j.services.AccountHttpService;

/**
 * API class for the java lichess wrapper.
 */
public class Lichess4J {
    private @NonNull AccountService accountService;

    public Lichess4J(@NonNull String bearerToken) {
        HttpRequestFactory httpRequestFactory = new HttpRequestFactory(bearerToken);
        HttpBaseClient httpBaseClient = new HttpBaseClient(GsonFactory.getGson(), HttpClient
                .newHttpClient(), httpRequestFactory);

        this.accountService = new AccountHttpService(httpBaseClient);
    }

    public AccountService account() {
        return this.accountService;
    }
}

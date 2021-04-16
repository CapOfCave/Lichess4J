package me.kecker.lichess4j.api;

import java.net.http.HttpClient;
import lombok.NonNull;
import me.kecker.lichess4j.config.ApiUrl;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.utils.GsonFactory;
import me.kecker.lichess4j.services.AccountHttpService;

/**
 * API class for the java lichess wrapper.
 */
public class Lichess4J {
    private @NonNull AccountService accountService;

    public Lichess4J(@NonNull String bearerToken) {
        HttpBaseClient httpBaseClient = createHttpBaseClient(bearerToken);

        this.accountService = new AccountHttpService(httpBaseClient);
    }

    public AccountService account() {
        return this.accountService;
    }

    HttpBaseClient createHttpBaseClient(String bearerToken) {
        return new HttpBaseClient(ApiUrl.BASE_URL, bearerToken, GsonFactory.getGson(), HttpClient
                .newHttpClient());
    }
}

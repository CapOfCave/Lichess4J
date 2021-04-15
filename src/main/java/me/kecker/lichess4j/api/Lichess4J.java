package me.kecker.lichess4j.api;

import lombok.NonNull;
import me.kecker.lichess4j.config.ApiUrl;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.utils.GsonFactory;
import me.kecker.lichess4j.services.AccountHttpService;

/**
 * API class for the java lichess wrapper.
 */
public class Lichess4J {
    private @NonNull String bearerToken;

    private HttpBaseClient httpBaseClient;

    private AccountService accountService;

    public Lichess4J(@NonNull String bearerToken) {
        this.bearerToken = bearerToken;
        this.setup();
    }

    private void setup() {
        this.httpBaseClient = new HttpBaseClient(ApiUrl.BASE_URL, this.bearerToken, GsonFactory
                .getGson());
        this.accountService = new AccountHttpService(this.httpBaseClient);
    }
    
    public AccountService account() {
        return this.accountService;
    }

}

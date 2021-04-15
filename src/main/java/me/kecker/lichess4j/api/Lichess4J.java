package me.kecker.lichess4j.api;

import java.io.IOException;
import lombok.NonNull;
import me.kecker.lichess4j.config.ApiUrl;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;
import me.kecker.lichess4j.http.utils.GsonFactory;
import me.kecker.lichess4j.model.Account;
import me.kecker.lichess4j.services.AccountHttpService;

/**
 * API class for the java lichess wrapper.
 */
public class Lichess4J {
    private @NonNull String bearerToken;

    private HttpBaseClient httpBaseClient;
    private AccountService accountApi;

    public Lichess4J(@NonNull String bearerToken) {
        this.bearerToken = bearerToken;
        this.setup();
    }

    public Account getAccountInfo() throws IOException, InterruptedException,
            UnauthorizedException, IllegalStatusCodeException {
        return this.accountApi.getAccountInfo();
    }

    private void setup() {
        this.httpBaseClient = new HttpBaseClient(ApiUrl.BASE_URL, this.bearerToken, GsonFactory
                .getGson());
        this.accountApi = new AccountHttpService(this.httpBaseClient);
    }

}

package me.kecker.lichess4j.api;

import java.io.IOException;
import lombok.NonNull;
import me.kecker.lichess4j.model.Account;
import me.kecker.lichess4j.services.AccountRetrofitService;

/**
 * API class for the java lichess wrapper.
 */
public class Lichess4J {
    private @NonNull String bearerToken;

    private AccountService accountApi;

    public Lichess4J(@NonNull String bearerToken) {
        this.bearerToken = bearerToken;
        this.setup();
    }

    public Account getAccountInfo() throws IOException {
        return this.accountApi.getAccountInfo(this.bearerToken);
    }

    private void setup() {
        this.accountApi = new AccountRetrofitService();
    }

}

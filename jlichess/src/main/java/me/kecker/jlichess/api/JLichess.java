package me.kecker.jlichess.api;

import java.io.IOException;
import lombok.NonNull;
import me.kecker.jlichess.model.Account;
import me.kecker.jlichess.services.AccountRetrofitService;

/**
 * API class for the java lichess wrapper.
 */
public class JLichess {
    private @NonNull String bearerToken;

    private AccountService accountApi;

    public JLichess(@NonNull String bearerToken) {
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

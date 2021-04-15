package me.kecker.lichess4j.services;

import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import me.kecker.lichess4j.api.AccountService;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;
import me.kecker.lichess4j.model.Account;

@AllArgsConstructor
public class AccountHttpService implements AccountService {

    @NonNull
    private HttpBaseClient httpBaseClient;

    @Override
    public Account getAccountInfo() throws IOException, InterruptedException, UnauthorizedException,
            IllegalStatusCodeException {
        Account accountInfoResoponse = this.httpBaseClient.get("https://lichess.org/api/account",
                Account.class);
        return accountInfoResoponse;
    }
}

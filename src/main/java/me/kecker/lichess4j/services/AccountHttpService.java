package me.kecker.lichess4j.services;

import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import me.kecker.lichess4j.api.AccountService;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;
import me.kecker.lichess4j.model.account.Account;
import me.kecker.lichess4j.model.account.Email;

@AllArgsConstructor
public class AccountHttpService implements AccountService {

    @NonNull
    private HttpBaseClient httpBaseClient;

    @Override
    public Account getAccountInfo() throws IOException, InterruptedException, UnauthorizedException,
            IllegalStatusCodeException {
        Account accountInfoResoponse = this.httpBaseClient.get("account", Account.class);
        return accountInfoResoponse;
    }

    @Override
    public String getEmail() throws IllegalStatusCodeException, IOException, InterruptedException {
        Email emailResponse = this.httpBaseClient.get("email", Email.class);
        return emailResponse.getEmail();
    }
}

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
import me.kecker.lichess4j.model.account.KidModeStatusWrapper;
import me.kecker.lichess4j.model.account.Preferences;
import me.kecker.lichess4j.model.account.PreferencesWrapper;
import me.kecker.lichess4j.model.enums.KidModeStatus;

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

    @Override
    public Preferences getPreferenes() throws IllegalStatusCodeException, IOException,
            InterruptedException {
        PreferencesWrapper emailResponse = this.httpBaseClient.get("preferences",
                PreferencesWrapper.class);
        return emailResponse.getPreferences();
    }

    @Override
    public KidModeStatus getKidModeStatus() throws IllegalStatusCodeException, IOException,
            InterruptedException {
        KidModeStatusWrapper kidModeStatusResponse = this.httpBaseClient.get("kid", KidModeStatusWrapper.class);
        return KidModeStatus.of(kidModeStatusResponse.getKidModeStatus());
    }
}

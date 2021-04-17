package me.kecker.lichess4j.api;

import java.io.IOException;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;
import me.kecker.lichess4j.model.account.Account;
import me.kecker.lichess4j.model.account.Preferences;
import me.kecker.lichess4j.model.enums.KidModeStatus;

public interface AccountService {

    Account getAccountInfo() throws IOException, InterruptedException, UnauthorizedException,
            IllegalStatusCodeException;

    String getEmail() throws IllegalStatusCodeException, IOException, InterruptedException;

    Preferences getPreferenes() throws IllegalStatusCodeException, IOException,
            InterruptedException;

    KidModeStatus getKidModeStatus() throws IllegalStatusCodeException, IOException,
            InterruptedException;

    void setKidModeStatus(KidModeStatus newKidModeStatus) throws IllegalStatusCodeException,
            IOException, InterruptedException;
}

package me.kecker.lichess4j.api;

import java.io.IOException;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;
import me.kecker.lichess4j.model.Account;

public interface AccountService {

    Account getAccountInfo() throws IOException, InterruptedException, UnauthorizedException,
            IllegalStatusCodeException;
}

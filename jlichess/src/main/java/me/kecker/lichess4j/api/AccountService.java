package me.kecker.lichess4j.api;

import java.io.IOException;
import me.kecker.lichess4j.model.Account;

public interface AccountService {

    Account getAccountInfo(String bearerToken) throws IOException;
}

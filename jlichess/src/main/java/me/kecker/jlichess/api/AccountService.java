package me.kecker.jlichess.api;

import java.io.IOException;
import me.kecker.jlichess.model.Account;

public interface AccountService {

  Account getAccountInfo(String bearerToken) throws IOException;
}

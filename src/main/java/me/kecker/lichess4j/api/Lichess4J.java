package me.kecker.lichess4j.api;

import java.net.http.HttpClient;
import lombok.NonNull;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.base.HttpRequestFactory;
import me.kecker.lichess4j.http.utils.GsonFactory;
import me.kecker.lichess4j.services.AccountHttpService;
import me.kecker.lichess4j.services.BoardHttpService;
import me.kecker.lichess4j.services.BotHttpService;
import me.kecker.lichess4j.services.UsersHttpService;

/**
 * API class for the java lichess wrapper.
 */
public class Lichess4J {
    private @NonNull AccountService accountService;
    private @NonNull UsersService usersService;
    private @NonNull BotService botService;
    private @NonNull BoardService boardService;

    public Lichess4J(@NonNull String bearerToken) {
        HttpRequestFactory httpRequestFactory = new HttpRequestFactory(bearerToken);
        HttpBaseClient httpBaseClient = new HttpBaseClient(GsonFactory.getGson(), HttpClient
                .newHttpClient(), httpRequestFactory);

        this.accountService = new AccountHttpService(httpBaseClient);
        this.usersService = new UsersHttpService(httpBaseClient);
        this.botService = new BotHttpService(httpBaseClient);
        this.boardService = new BoardHttpService(httpBaseClient);
    }

    public AccountService account() {
        return this.accountService;
    }

    public UsersService users() {
        return this.usersService;
    }

    public BotService bot() {
        return this.botService;
    }

    public BoardService board() {
        return this.boardService;
    }
}

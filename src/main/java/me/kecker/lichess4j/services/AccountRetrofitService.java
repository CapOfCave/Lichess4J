package me.kecker.lichess4j.services;

import java.io.IOException;
import me.kecker.lichess4j.api.AccountService;
import me.kecker.lichess4j.config.ApiUrl;
import me.kecker.lichess4j.http.clients.AccountHttpClient;
import me.kecker.lichess4j.model.Account;
import me.kecker.lichess4j.services.utils.RetrofitFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AccountRetrofitService implements AccountService {

    private AccountHttpClient httpClient;

    public AccountRetrofitService() {
        this(RetrofitFactory.getOrCreateRetrofit(ApiUrl.BASE_URL));
    }

    public AccountRetrofitService(Retrofit retrofit) {
        this.httpClient = retrofit.create(AccountHttpClient.class);
    }

    @Override
    public Account getAccountInfo(String bearerToken) throws IOException {
        Response<Account> accountInfoResoponse = this.httpClient.getAccountInfo("Bearer "
                + bearerToken)
                .execute();
        return accountInfoResoponse.body();
    }
}

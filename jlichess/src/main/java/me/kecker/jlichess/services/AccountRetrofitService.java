package me.kecker.jlichess.services;

import java.io.IOException;
import retrofit2.Response;
import retrofit2.Retrofit;

import me.kecker.jlichess.api.AccountService;
import me.kecker.jlichess.config.ApiUrl;
import me.kecker.jlichess.http.clients.AccountHttpClient;
import me.kecker.jlichess.model.Account;
import me.kecker.jlichess.services.utils.RetrofitFactory;

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

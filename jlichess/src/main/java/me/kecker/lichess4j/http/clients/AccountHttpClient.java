package me.kecker.lichess4j.http.clients;

import me.kecker.lichess4j.model.Account;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AccountHttpClient {

    @GET("account")
    Call<Account> getAccountInfo(@Header("Authorization") String authorization);

}

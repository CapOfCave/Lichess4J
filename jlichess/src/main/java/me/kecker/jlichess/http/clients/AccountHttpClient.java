package me.kecker.jlichess.http.clients;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import me.kecker.jlichess.model.Account;

public interface AccountHttpClient {

  @GET("account")
  Call<Account> getAccountInfo(@Header("Authorization") String authorization);

}

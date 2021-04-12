package me.kecker.jlichess.services.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import me.kecker.jlichess.config.ApiUrl;
import me.kecker.jlichess.http.adapters.LocalDateTimeTypeAdapter;

public class RetrofitFactory {

  private static Retrofit cache = null;

  public static Retrofit getOrCreateRetrofit() {
    if (cache != null) {
      return cache;
    }
    Gson gson =
        new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
          .create();
    Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build();
    cache = retrofit;
    return retrofit;
  }
  
  public static <T> T createHttpClient(Class<T> clazz){
    return getOrCreateRetrofit().create(clazz);
  }
}

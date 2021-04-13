package me.kecker.jlichess.services.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import me.kecker.jlichess.http.adapters.LocalDateTimeTypeAdapter;

public class RetrofitFactory {

    private static Map<HttpUrl, Retrofit> cache = new HashMap<>();

    public static Retrofit getOrCreateRetrofit(String baseUrl) {
        return getOrCreateRetrofit(HttpUrl.get(baseUrl));
    }

    public static Retrofit getOrCreateRetrofit(HttpUrl baseUrl) {
        if (cache.containsKey(baseUrl)) {
            return cache.get(baseUrl);
        }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                new LocalDateTimeTypeAdapter())
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        cache.put(baseUrl, retrofit);
        return retrofit;
    }
    
    public static void clearCache() {
        cache.clear();
    }
}

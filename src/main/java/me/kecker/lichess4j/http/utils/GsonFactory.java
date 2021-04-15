package me.kecker.lichess4j.http.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import me.kecker.lichess4j.http.adapters.LocalDateTimeTypeAdapter;

public class GsonFactory {

    public static Gson getGson() {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                new LocalDateTimeTypeAdapter())
                .create();
    }
    
    private GsonFactory() {
        // this class should not be instantiated
    }

}

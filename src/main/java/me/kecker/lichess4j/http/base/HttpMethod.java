package me.kecker.lichess4j.http.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Complete enumeration of http methods supported by the lichess.org api.
 *
 */
@AllArgsConstructor
@Getter
public enum HttpMethod {

    GET("GET"),
    POST("POST"),
    DELETE("DELETE");

    private String key;
}

package me.kecker.lichess4j.http.base;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HttpRequestFactory {

    private static final String BASE_URL = "https://lichess.org/api";

    private String bearerToken;

    public HttpRequest createGetRequest(String path, Map<String, String> parameters) {
        return HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + this.bearerToken)
                .uri(createURI(path, parameters))
                .build();
    }

    URI createURI(String path, Map<String, String> parameters) {
        return URI.create(BASE_URL + withStartingSlash(path) + "?" + createQuery(parameters));
    }

    private String withStartingSlash(String relativePath) {
        if (relativePath.startsWith("/")) {
            return relativePath;
        }
        return "/" + relativePath;
    }

    private String createQuery(Map<String, String> parameters) {
        return parameters.entrySet()
                .stream()
                .map(entry -> String.format("%s=%s", urlEncode(entry.getKey()), urlEncode(entry
                        .getValue())))
                .collect(Collectors.joining("&"));
    }

    private String urlEncode(String string) {
        return URLEncoder.encode(string, StandardCharsets.UTF_8);
    }

}

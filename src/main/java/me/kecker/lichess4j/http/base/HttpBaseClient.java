package me.kecker.lichess4j.http.base;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;

@RequiredArgsConstructor
public class HttpBaseClient {

    @NonNull
    private String baseUrl;
    @NonNull
    private String bearerToken;
    @NonNull
    private Gson gson;

    public <T> T get(String url, Class<T> entityClass) throws IOException, InterruptedException,
            IllegalStatusCodeException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + this.bearerToken)
                .uri(URI.create(this.baseUrl + url))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        validateStatusCode(response.statusCode());

        return this.gson.fromJson(response.body(), entityClass);

    }

    private void validateStatusCode(int statusCode) throws IllegalStatusCodeException {
        if (statusCode == 401) {
            throw new UnauthorizedException();
        }
        if (statusCode != 200) {
            throw new IllegalStatusCodeException(statusCode);
        }
    }
}

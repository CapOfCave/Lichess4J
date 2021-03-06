package me.kecker.lichess4j.http.base;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collections;
import java.util.Map;
import javax.lang.model.type.NullType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;

@RequiredArgsConstructor
public class HttpBaseClient {

    @NonNull
    private Gson gson;
    @NonNull
    private HttpClient httpClient;
    @NonNull
    private HttpRequestFactory requestFactory;

    public static final Class<NullType> NO_RESPONSE = NullType.class;

    public <T> T get(String endpoint, String path, Class<T> responseClass) throws IOException,
            InterruptedException, IllegalStatusCodeException {
        
        return get(endpoint, path, Collections.emptyMap(), responseClass);
    }

    public <T> T get(String endpoint, String path, Map<String, String> parameters,
            Class<T> responseClass) throws IOException, InterruptedException,
            IllegalStatusCodeException {

        return request(HttpMethod.GET, endpoint, path, parameters, responseClass, BodyPublishers
                .noBody());
    }

    public <T> T post(String endpoint, String path, Map<String, String> parameters,
            Class<T> responseClass, BodyPublisher bodyPublisher) throws IOException,
            InterruptedException, IllegalStatusCodeException {
        
        return request(HttpMethod.POST, endpoint, path, parameters, responseClass, bodyPublisher);
    }

    private <T> T request(HttpMethod method, String endpoint, String path,
            Map<String, String> parameters, Class<T> responseClass, BodyPublisher bodyPublisher)
            throws IOException, InterruptedException, IllegalStatusCodeException {

        HttpRequest request = requestFactory.createRequest(method, endpoint, path, parameters,
                bodyPublisher);
        HttpResponse<String> response = this.httpClient.send(request, BodyHandlers.ofString());
        validateStatusCode(response.statusCode());

        if (responseClass == null || responseClass.equals(NO_RESPONSE)) {
            return null;
        }
        return this.gson.fromJson(response.body(), responseClass);
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

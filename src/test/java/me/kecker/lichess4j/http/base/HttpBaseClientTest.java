package me.kecker.lichess4j.http.base;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.pgssoft.httpclient.HttpClientMock;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Collections;
import java.util.Map;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;
import me.kecker.lichess4j.model.account.Account;
import me.kecker.lichess4j.test.providers.AccountTestProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HttpBaseClientTest {

    private static final String BASE_URL = "https://lichess.org/api/";
    private static final String BEARER_TOKEN = "123abc456def";
    private static final String URL = "url";
    private static final String RESPONSE_BODY = "{\"success\": true}";
    private static final Class<Account> RESPONSE_CLASS = Account.class;
    private static final Map<String, String> PARAMETERS = Map.of("a", "1", "b", "2");

    private HttpBaseClient objectUnderTest;

    @Mock
    private Gson gsonMock;
    @Mock
    private HttpRequestFactory httpRequestFactoryMock;
    private HttpClientMock httpClientMock;

    @Before
    public void setup() {
        this.httpClientMock = new HttpClientMock();
        this.objectUnderTest = new HttpBaseClient(this.gsonMock, this.httpClientMock,
                this.httpRequestFactoryMock);
    }

    @Test
    public void get_happyDay_callsLichessApi() throws IllegalStatusCodeException, IOException,
            InterruptedException {

        mockHttpRequestFactory(Collections.emptyMap());
        this.httpClientMock.onGet(BASE_URL + URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .doReturn(RESPONSE_BODY);
        when(this.gsonMock.fromJson(RESPONSE_BODY, RESPONSE_CLASS)).thenReturn(AccountTestProvider
                .getAccount());

        Account result = this.objectUnderTest.get(URL, RESPONSE_CLASS);

        assertEquals(AccountTestProvider.getAccount(), result);
        this.httpClientMock.verify()
                .get(BASE_URL + URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN);

    }

    @Test(expected = UnauthorizedException.class)
    public void get_unauthorized_throwsUnautorizedException() throws IllegalStatusCodeException,
            IOException, InterruptedException {

        mockHttpRequestFactory(Collections.emptyMap());
        this.httpClientMock.onGet(BASE_URL + URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .doReturnStatus(401);

        this.objectUnderTest.get(URL, RESPONSE_CLASS);

    }

    @Test(expected = IllegalStatusCodeException.class)
    public void get_unidentifiedStatusCode_throwsIllegalStatusCodeException()
            throws IllegalStatusCodeException, IOException, InterruptedException {
        mockHttpRequestFactory(Collections.emptyMap());
        this.httpClientMock.onGet(BASE_URL + URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .doReturnStatus(501);

        this.objectUnderTest.get(URL, RESPONSE_CLASS);

    }

    public void get_withParameters_callsLichessAPI() throws IllegalStatusCodeException, IOException,
            InterruptedException {
        mockHttpRequestFactory(PARAMETERS);
        this.httpClientMock.onGet(BASE_URL + URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .doReturn(RESPONSE_BODY);
        when(this.gsonMock.fromJson(RESPONSE_BODY, RESPONSE_CLASS)).thenReturn(AccountTestProvider
                .getAccount());

        Account result = this.objectUnderTest.get(URL, PARAMETERS, RESPONSE_CLASS);

        assertEquals(AccountTestProvider.getAccount(), result);
        this.httpClientMock.verify()
                .get(BASE_URL + URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN);

    }

    private void mockHttpRequestFactory(Map<String, String> parameters) {
        when(this.httpRequestFactoryMock.createGetRequest(URL, parameters)).thenReturn(
                createHttpRequest());
    }

    private HttpRequest createHttpRequest() {
        return HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .uri(URI.create(BASE_URL + URL))
                .build();
    }
}

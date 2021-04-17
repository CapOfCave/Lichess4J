package me.kecker.lichess4j.http.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.pgssoft.httpclient.HttpClientMock;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Collections;
import java.util.Map;
import javax.lang.model.type.NullType;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;
import me.kecker.lichess4j.model.account.Account;
import me.kecker.lichess4j.test.providers.AccountTestProvider;
import org.hamcrest.text.IsEmptyString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HttpBaseClientTest {

    private static final String BASE_URL = "https://lichess.org/api/";
    private static final String BEARER_TOKEN = "123abc456def";
    private static final String ENDPOINT = "endpoint";
    private static final String PATH = "path";
    private static final String FULL_URL = BASE_URL + ENDPOINT + "/" + PATH;

    private static final String RESPONSE_BODY = "{\"success\": true}";
    private static final Class<Account> RESPONSE_CLASS = Account.class;
    private static final Map<String, String> PARAMETERS = Map.of("a", "1", "b", "2");

    private HttpBaseClient objectUnderTest;

    @Mock
    private Gson gsonMock;
    @Mock
    private HttpRequestFactory httpRequestFactoryMock;
    private HttpClientMock httpClientMock;

    @Captor
    private ArgumentCaptor<BodyPublisher> bodyPublisherCaptor;

    @Before
    public void setup() {
        this.httpClientMock = new HttpClientMock();
        this.objectUnderTest = new HttpBaseClient(this.gsonMock, this.httpClientMock,
                this.httpRequestFactoryMock);
    }

    @Test
    public void get_happyDay_callsLichessApi() throws IllegalStatusCodeException, IOException,
            InterruptedException {

        mockHttpRequestFactory(HttpMethod.GET, PATH, Collections.emptyMap(), FULL_URL);

        this.httpClientMock.onGet(FULL_URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .doReturn(RESPONSE_BODY);
        when(this.gsonMock.fromJson(RESPONSE_BODY, RESPONSE_CLASS)).thenReturn(AccountTestProvider
                .getAccount());

        Account result = this.objectUnderTest.get(ENDPOINT, PATH, RESPONSE_CLASS);

        assertEquals(AccountTestProvider.getAccount(), result);
        this.httpClientMock.verify()
                .get(FULL_URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN);
        assertBodyWasEmpty();
    }

    @Test(expected = UnauthorizedException.class)
    public void get_unauthorized_throwsUnautorizedException() throws IllegalStatusCodeException,
            IOException, InterruptedException {

        mockHttpRequestFactory(HttpMethod.GET, PATH, Collections.emptyMap(), FULL_URL);
        this.httpClientMock.onGet(FULL_URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .doReturnStatus(401);

        this.objectUnderTest.get(ENDPOINT, PATH, RESPONSE_CLASS);
    }

    @Test(expected = IllegalStatusCodeException.class)
    public void get_unidentifiedStatusCode_throwsIllegalStatusCodeException()
            throws IllegalStatusCodeException, IOException, InterruptedException {
        mockHttpRequestFactory(HttpMethod.GET, PATH, Collections.emptyMap(), FULL_URL);
        this.httpClientMock.onGet(FULL_URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .doReturnStatus(501);

        this.objectUnderTest.get(ENDPOINT, PATH, RESPONSE_CLASS);
    }

    @Test
    public void get_withParameters_callsLichessAPI() throws IllegalStatusCodeException, IOException,
            InterruptedException {
        mockHttpRequestFactory(HttpMethod.GET, PATH, PARAMETERS, FULL_URL);
        this.httpClientMock.onGet(FULL_URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .doReturn(RESPONSE_BODY);
        when(this.gsonMock.fromJson(RESPONSE_BODY, RESPONSE_CLASS)).thenReturn(AccountTestProvider
                .getAccount());

        Account result = this.objectUnderTest.get(ENDPOINT, PATH, PARAMETERS, RESPONSE_CLASS);

        assertEquals(AccountTestProvider.getAccount(), result);
        this.httpClientMock.verify()
                .get(FULL_URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN);
        assertBodyWasEmpty();

    }

    @Test
    public void get_withEmptySubPath_callsLichessApi() throws IllegalStatusCodeException,
            IOException, InterruptedException {

        mockHttpRequestFactory(HttpMethod.GET, null, Collections.emptyMap(), BASE_URL + ENDPOINT);
        this.httpClientMock.onGet(BASE_URL + ENDPOINT)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .doReturn(RESPONSE_BODY);
        when(this.gsonMock.fromJson(RESPONSE_BODY, RESPONSE_CLASS)).thenReturn(AccountTestProvider
                .getAccount());

        Account result = this.objectUnderTest.get(ENDPOINT, null, RESPONSE_CLASS);

        assertEquals(AccountTestProvider.getAccount(), result);
        this.httpClientMock.verify()
                .get(BASE_URL + ENDPOINT)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN);
        assertBodyWasEmpty();
    }

    @Test
    public void post_withoutExpectingResponse_callsLichessAPI() throws IllegalStatusCodeException,
            IOException, InterruptedException {
        mockHttpRequestFactory(HttpMethod.POST, PATH, PARAMETERS, FULL_URL);
        this.httpClientMock.onPost(FULL_URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .doReturn(RESPONSE_BODY);

        NullType response = this.objectUnderTest.post(ENDPOINT, PATH, PARAMETERS,
                HttpBaseClient.NO_RESPONSE, BodyPublishers.noBody());

        assertNull(response);
        this.httpClientMock.verify()
                .post(FULL_URL)
                .withBody(new IsEmptyString())
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN);
        verify(this.gsonMock, never()).fromJson(Mockito.anyString(), Mockito.any());
        assertBodyWasEmpty();
    }
    
    @Test
    public void post_withoutNullResponseParameter_callsLichessAPI() throws IllegalStatusCodeException,
            IOException, InterruptedException {
        mockHttpRequestFactory(HttpMethod.POST, PATH, PARAMETERS, FULL_URL);
        this.httpClientMock.onPost(FULL_URL)
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .doReturn(RESPONSE_BODY);

        NullType response = this.objectUnderTest.post(ENDPOINT, PATH, PARAMETERS,
                null, BodyPublishers.noBody());

        assertNull(response);
        this.httpClientMock.verify()
                .post(FULL_URL)
                .withBody(new IsEmptyString())
                .withHeader("Authorization", "Bearer " + BEARER_TOKEN);
        verify(this.gsonMock, never()).fromJson(Mockito.anyString(), Mockito.any());
        assertBodyWasEmpty();
    }


    private void mockHttpRequestFactory(HttpMethod httpMethod, String path,
            Map<String, String> parameters, String returnedUrl) {
        when(this.httpRequestFactoryMock.createRequest(eq(httpMethod), eq(ENDPOINT), eq(path), eq(
                parameters), this.bodyPublisherCaptor.capture())).thenReturn(createHttpRequest(
                        httpMethod, returnedUrl));
    }

    private HttpRequest createHttpRequest(HttpMethod httpMethod, String fullUrl) {
        return HttpRequest.newBuilder()
                .method(httpMethod.getKey(), BodyPublishers.noBody())
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .uri(URI.create(fullUrl))
                .build();
    }

    private void assertBodyWasEmpty() {
        assertEquals(0L, this.bodyPublisherCaptor.getValue()
                .contentLength());
    }

}

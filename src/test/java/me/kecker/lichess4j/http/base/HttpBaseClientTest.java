package me.kecker.lichess4j.http.base;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.pgssoft.httpclient.HttpClientMock;
import java.io.IOException;
import java.net.http.HttpClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.http.exceptions.UnauthorizedException;
import me.kecker.lichess4j.model.Account;
import me.kecker.lichess4j.test.providers.AccountTestProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HttpBaseClientTest {

    private static final String BASE_URL = "https://lichess.org/api/";
    private static final String BEARER_TOKEN = "123abc456def";
    private static final String URL = "url";
    private static final String RESPONSE_BODY = "{\"success\": true}";
    private static final Class<Account> RESPONSE_CLASS = Account.class;

    private HttpBaseClient objectUnderTest;

    @Mock
    private Gson gsonMock;
    private HttpClientMock httpClientMock;

    @Before
    public void setup() {
        this.httpClientMock = new HttpClientMock();
        this.objectUnderTest = new HttpBaseClient(BASE_URL, BEARER_TOKEN, gsonMock);
    }

    @Test
    public void get_happyDay_callsLichessApi() throws IllegalStatusCodeException, IOException,
            InterruptedException {
        try (MockedStatic<HttpClient> mockedHttpClient = Mockito.mockStatic(HttpClient.class)) {
            mockedHttpClient.when(HttpClient::newHttpClient)
                    .thenReturn(this.httpClientMock);

            this.httpClientMock.onGet(BASE_URL + URL)
                    .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                    .doReturn(RESPONSE_BODY);

            when(this.gsonMock.fromJson(RESPONSE_BODY, RESPONSE_CLASS)).thenReturn(
                    AccountTestProvider.getAccount());

            Account result = this.objectUnderTest.get(URL, RESPONSE_CLASS);

            assertEquals(AccountTestProvider.getAccount(), result);

            this.httpClientMock.verify()
                    .get(BASE_URL + URL)
                    .withHeader("Authorization", "Bearer " + BEARER_TOKEN);

        }
    }

    @Test(expected = UnauthorizedException.class)
    public void get_unauthorized_throwsUnautorizedException() throws IllegalStatusCodeException,
            IOException, InterruptedException {
        try (MockedStatic<HttpClient> mockedHttpClient = Mockito.mockStatic(HttpClient.class)) {
            mockedHttpClient.when(HttpClient::newHttpClient)
                    .thenReturn(this.httpClientMock);

            this.httpClientMock.onGet(BASE_URL + URL)
                    .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                    .doReturnStatus(401);

            this.objectUnderTest.get(URL, RESPONSE_CLASS);

        }
    }
    
    @Test(expected = IllegalStatusCodeException.class)
    public void get_unidentifiedStatusCode_throwsIllegalStatusCodeException() throws IllegalStatusCodeException,
            IOException, InterruptedException {
        try (MockedStatic<HttpClient> mockedHttpClient = Mockito.mockStatic(HttpClient.class)) {
            mockedHttpClient.when(HttpClient::newHttpClient)
                    .thenReturn(this.httpClientMock);

            this.httpClientMock.onGet(BASE_URL + URL)
                    .withHeader("Authorization", "Bearer " + BEARER_TOKEN)
                    .doReturnStatus(501);

            this.objectUnderTest.get(URL, RESPONSE_CLASS);

        }
    }

}

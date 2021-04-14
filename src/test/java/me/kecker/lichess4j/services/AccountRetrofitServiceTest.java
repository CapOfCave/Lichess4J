package me.kecker.lichess4j.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import me.kecker.lichess4j.model.Account;
import me.kecker.lichess4j.services.utils.RetrofitFactory;
import me.kecker.lichess4j.test.providers.AccountTestProvider;
import me.kecker.lichess4j.test.utils.MockResponseUtils;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;

public class AccountRetrofitServiceTest {

    private static final String ACCOUNT_FILE_URL = "account.json";
    private static final String BEARER_TOKEN = "1234567890";

    private AccountRetrofitService objectUnderTest;

    private MockWebServer mockWebServer;

    @Before
    public void setup() throws IOException {
        this.mockWebServer = new MockWebServer();
        this.mockWebServer.start();

        Retrofit retrofit = RetrofitFactory.getOrCreateRetrofit(this.mockWebServer.url("/"));
        this.objectUnderTest = new AccountRetrofitService(retrofit);
    }

    @After
    public void tearDown() throws IOException {
        this.mockWebServer.close();
        RetrofitFactory.clearCache();
    }

    @Test
    public void getAccountInfo_happyDay_returnsAccountInfo() throws IOException, InterruptedException {
        this.mockWebServer.enqueue(MockResponseUtils.fromFile(ACCOUNT_FILE_URL,
                AccountRetrofitServiceTest.class));

        Account result = this.objectUnderTest.getAccountInfo(BEARER_TOKEN);

        // assert request is correct
        RecordedRequest request = this.mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/account", request.getPath());
        assertEquals("Bearer " + BEARER_TOKEN, request.getHeader("Authorization"));
        
        // assert response is correct
        assertNotNull(result);
        assertThat(result).isEqualTo(AccountTestProvider.getAccount())
                .usingRecursiveComparison();
        
    }
}

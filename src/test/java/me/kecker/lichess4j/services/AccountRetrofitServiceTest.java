package me.kecker.lichess4j.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import me.kecker.lichess4j.model.Account;
import me.kecker.lichess4j.services.AccountRetrofitService;
import me.kecker.lichess4j.services.utils.RetrofitFactory;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;

public class AccountRetrofitServiceTest {

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
    public void getAccountInfo_happyDay_returnsAccountInfo() throws IOException {
        this.mockWebServer.enqueue(new MockResponse().setBody("{\"id\": \"1234\"}"));

        Account result = this.objectUnderTest.getAccountInfo(BEARER_TOKEN);
        assertNotNull(result);
        assertEquals(1234, result.getId());
    }
}

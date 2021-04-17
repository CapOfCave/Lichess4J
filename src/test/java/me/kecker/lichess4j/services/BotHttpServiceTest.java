package me.kecker.lichess4j.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.Collections;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BotHttpServiceTest {

    private static final String ENDPOINT = "bot";
    private static final String UPGRADE = "account/upgrade";

    private BotHttpService objectUnderTest;

    @Mock
    private HttpBaseClient httpBaseClientMock;

    @Captor
    private ArgumentCaptor<BodyPublisher> bodyPublisherCaptor;

    @Before
    public void setup() throws IOException {
        this.objectUnderTest = new BotHttpService(this.httpBaseClientMock);
    }

    @Test
    public void upgrade_happyDay_postsCorrectRequest() throws IOException, InterruptedException,
            IllegalStatusCodeException {

        when(this.httpBaseClientMock.post(eq(ENDPOINT), eq(UPGRADE), eq(Collections.emptyMap()), eq(
                HttpBaseClient.NO_RESPONSE), this.bodyPublisherCaptor.capture())).thenReturn(null);

        this.objectUnderTest.upgrade();

        verify(this.httpBaseClientMock, times(1)).post(eq(ENDPOINT), eq(UPGRADE), eq(Collections
                .emptyMap()), eq(HttpBaseClient.NO_RESPONSE), Mockito.any());
        assertEquals(0L, this.bodyPublisherCaptor.getValue()
                .contentLength());
    }
}

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
public class BoardHttpServiceTest {

    private static final String ENDPOINT = "board";
    private static final String GAME_ID = "gameId";
    private static final String NON_URL_CONFORM_GAME_ID = "g+ameId";
    private static final String NON_URL_CONFORM_GAME_ID_ENCODED = "g%2BameId";

    private BoardHttpService objectUnderTest;

    @Mock
    private HttpBaseClient httpBaseClientMock;

    @Captor
    private ArgumentCaptor<BodyPublisher> bodyPublisherCaptor;

    @Before
    public void setup() throws IOException {
        this.objectUnderTest = new BoardHttpService(this.httpBaseClientMock);
    }

    @Test
    public void upgrade_happyDay_postsCorrectRequest() throws IOException, InterruptedException,
            IllegalStatusCodeException {

        when(this.httpBaseClientMock.post(eq(ENDPOINT), eq(getAbortPath(GAME_ID)), eq(Collections
                .emptyMap()), eq(HttpBaseClient.NO_RESPONSE), this.bodyPublisherCaptor.capture()))
                        .thenReturn(null);

        this.objectUnderTest.abortGame(GAME_ID);

        verify(this.httpBaseClientMock, times(1)).post(eq(ENDPOINT), eq(getAbortPath(GAME_ID)), eq(
                Collections.emptyMap()), eq(HttpBaseClient.NO_RESPONSE), Mockito.any());
        assertEquals(0L, this.bodyPublisherCaptor.getValue()
                .contentLength());
    }

    @Test
    public void upgrade_illegalGameId_postsEncodedRequest() throws IOException,
            InterruptedException, IllegalStatusCodeException {

        when(this.httpBaseClientMock.post(eq(ENDPOINT), eq(getAbortPath(
                NON_URL_CONFORM_GAME_ID_ENCODED)), eq(Collections.emptyMap()), eq(
                        HttpBaseClient.NO_RESPONSE), this.bodyPublisherCaptor.capture()))
                                .thenReturn(null);

        this.objectUnderTest.abortGame(NON_URL_CONFORM_GAME_ID);

        verify(this.httpBaseClientMock, times(1)).post(eq(ENDPOINT), eq(getAbortPath(
                NON_URL_CONFORM_GAME_ID_ENCODED)), eq(Collections.emptyMap()), eq(
                        HttpBaseClient.NO_RESPONSE), Mockito.any());
        assertEquals(0L, this.bodyPublisherCaptor.getValue()
                .contentLength());
    }

    private String getAbortPath(String gameId) {
        return "game/" + gameId + "/abort";
    }

}
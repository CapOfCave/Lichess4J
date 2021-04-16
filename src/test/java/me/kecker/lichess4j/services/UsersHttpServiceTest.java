package me.kecker.lichess4j.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.model.users.UserStatus;
import me.kecker.lichess4j.test.providers.UserStatusTestProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsersHttpServiceTest {

    private static final String ENDPOINT = "users";
    private static final String REAL_TIME_USERS_STATUS_URL = "status";

    private UsersHttpService objectUnderTest;

    @Mock
    private HttpBaseClient httpBaseClientMock;

    @Before
    public void setup() throws IOException {
        this.objectUnderTest = new UsersHttpService(httpBaseClientMock);
    }

    @Test
    public void getRealTimeUserStatus_happyDay_returnsUserStatus() throws IOException,
            InterruptedException, IllegalStatusCodeException {

        when(httpBaseClientMock.get(ENDPOINT, REAL_TIME_USERS_STATUS_URL, UserStatus.class))
                .thenReturn(UserStatusTestProvider.getFullUserStatus());

        UserStatus result = this.objectUnderTest.getRealTimeUserStatus();

        assertNotNull(result);
        assertThat(result.getOnline()
                .isPresent()).isTrue();
        assertThat(result.getPlaying()
                .isPresent()).isTrue();
        assertThat(result.getStreaming()
                .isPresent()).isTrue();
        assertThat(result.getPatron()
                .isPresent()).isTrue();

        assertThat(result).isEqualTo(UserStatusTestProvider.getFullUserStatus())
                .usingRecursiveComparison();

    }

    @Test
    public void getRealTimeUserStatus_responseWithMissingFields_returnsUserStatus()
            throws IOException, InterruptedException, IllegalStatusCodeException {

        when(httpBaseClientMock.get(ENDPOINT, REAL_TIME_USERS_STATUS_URL, UserStatus.class))
                .thenReturn(UserStatusTestProvider.getUserStatus());

        UserStatus result = this.objectUnderTest.getRealTimeUserStatus();

        assertNotNull(result);
        assertThat(result.getOnline()
                .isPresent()).isFalse();
        assertThat(result.getPlaying()
                .isPresent()).isFalse();
        assertThat(result.getStreaming()
                .isPresent()).isFalse();
        assertThat(result.getPatron()
                .isPresent()).isFalse();

        assertThat(result).isEqualTo(UserStatusTestProvider.getUserStatus())
                .usingRecursiveComparison();

    }

}

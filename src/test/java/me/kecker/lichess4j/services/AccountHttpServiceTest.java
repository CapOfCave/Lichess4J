package me.kecker.lichess4j.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.Map;
import javax.lang.model.type.NullType;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.model.account.Account;
import me.kecker.lichess4j.model.account.Email;
import me.kecker.lichess4j.model.account.KidModeStatusWrapper;
import me.kecker.lichess4j.model.account.Preferences;
import me.kecker.lichess4j.model.account.PreferencesWrapper;
import me.kecker.lichess4j.model.enums.KidModeStatus;
import me.kecker.lichess4j.test.providers.AccountTestProvider;
import me.kecker.lichess4j.test.providers.EmailTestProvider;
import me.kecker.lichess4j.test.providers.KidModeStatusTestProvider;
import me.kecker.lichess4j.test.providers.PreferencesTestProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountHttpServiceTest {

    private static final String ACCOUNT_BASE_URL = "account";
    private static final String EMAIL_URL = "email";
    private static final String PREFERENCES_URL = "preferences";
    private static final String KID_MODE_STATUS_URL = "kid";

    private AccountHttpService objectUnderTest;

    @Mock
    private HttpBaseClient httpBaseClientMock;

    @Captor
    private ArgumentCaptor<BodyPublisher> bodyPublisherCaptor;

    @Before
    public void setup() throws IOException {
        this.objectUnderTest = new AccountHttpService(httpBaseClientMock);
    }

    @Test
    public void getAccountInfo_happyDay_returnsAccountInfo() throws IOException,
            InterruptedException, IllegalStatusCodeException {

        when(httpBaseClientMock.get(ACCOUNT_BASE_URL, null, Account.class)).thenReturn(
                AccountTestProvider.getAccount());

        Account result = this.objectUnderTest.getAccountInfo();

        assertNotNull(result);
        assertThat(result).isEqualTo(AccountTestProvider.getAccount())
                .usingRecursiveComparison();

    }

    @Test
    public void getEmail_happyDay_returnsEmail() throws IOException, InterruptedException,
            IllegalStatusCodeException {

        when(httpBaseClientMock.get(ACCOUNT_BASE_URL, EMAIL_URL, Email.class)).thenReturn(
                EmailTestProvider.getEmail());

        String result = this.objectUnderTest.getEmail();

        assertNotNull(result);
        assertThat(result).isEqualTo(EmailTestProvider.EMAIL);

    }

    @Test
    public void getPreferences_happyDay_returnsPreferences() throws IOException,
            InterruptedException, IllegalStatusCodeException {

        when(httpBaseClientMock.get(ACCOUNT_BASE_URL, PREFERENCES_URL, PreferencesWrapper.class))
                .thenReturn(PreferencesTestProvider.getPreferencesWrapper());

        Preferences result = this.objectUnderTest.getPreferenes();

        assertNotNull(result);
        assertThat(result).isEqualTo(PreferencesTestProvider.getPreferences());

    }

    @Test
    public void getKidModeStatus_happyDay_returnKidModeStatus() throws IOException,
            InterruptedException, IllegalStatusCodeException {

        when(httpBaseClientMock.get(ACCOUNT_BASE_URL, KID_MODE_STATUS_URL,
                KidModeStatusWrapper.class)).thenReturn(KidModeStatusTestProvider
                        .getKidModeStatusWrapper());

        KidModeStatus result = this.objectUnderTest.getKidModeStatus();

        assertNotNull(result);
        assertThat(result).isEqualTo(KidModeStatusTestProvider.getKidModeStatus());

    }

    @Test
    public void setKidModeStatus_setToKid_setsKidModeStatus() throws IOException,
            InterruptedException, IllegalStatusCodeException {

        this.objectUnderTest.setKidModeStatus(KidModeStatus.KID);

        verify(httpBaseClientMock, times(1)).post(eq(ACCOUNT_BASE_URL), eq(KID_MODE_STATUS_URL), eq(
                Map.of("v", "true")), eq(NullType.class), this.bodyPublisherCaptor.capture());
        assertEquals(0L, this.bodyPublisherCaptor.getValue()
                .contentLength());
    }

    @Test
    public void setKidModeStatus_setToNoKid_setsKidModeStatus() throws IOException,
            InterruptedException, IllegalStatusCodeException {

        this.objectUnderTest.setKidModeStatus(KidModeStatus.NO_KID);

        verify(httpBaseClientMock, times(1)).post(eq(ACCOUNT_BASE_URL), eq(KID_MODE_STATUS_URL), eq(
                Map.of("v", "false")), eq(NullType.class), this.bodyPublisherCaptor.capture());
        assertEquals(0L, this.bodyPublisherCaptor.getValue()
                .contentLength());
    }
}

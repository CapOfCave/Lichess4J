package me.kecker.lichess4j.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.model.Account;
import me.kecker.lichess4j.test.providers.AccountTestProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountHttpServiceTest {

    private static final String EXPECTED_URL = "https://lichess.org/api/account";

    private AccountHttpService objectUnderTest;

    @Mock
    private HttpBaseClient httpBaseClientMock;

    @Before
    public void setup() throws IOException {
        this.objectUnderTest = new AccountHttpService(httpBaseClientMock);
    }

    @Test
    public void getAccountInfo_happyDay_returnsAccountInfo() throws IOException,
            InterruptedException, IllegalStatusCodeException {

        when(httpBaseClientMock.get(EXPECTED_URL, Account.class)).thenReturn(AccountTestProvider
                .getAccount());

        Account result = this.objectUnderTest.getAccountInfo();

        assertNotNull(result);
        assertThat(result).isEqualTo(AccountTestProvider.getAccount())
                .usingRecursiveComparison();

    }
}

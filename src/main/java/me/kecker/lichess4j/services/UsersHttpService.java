package me.kecker.lichess4j.services;

import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import me.kecker.lichess4j.api.UsersService;
import me.kecker.lichess4j.http.base.HttpBaseClient;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.model.users.UserStatus;

@AllArgsConstructor
public class UsersHttpService implements UsersService {

    private static final String ENDPOINT = "users";
    @NonNull
    private HttpBaseClient httpBaseClient;

    @Override
    public UserStatus getRealTimeUserStatus() throws IllegalStatusCodeException, IOException,
            InterruptedException {
        UserStatus statusResponse = this.httpBaseClient.get(ENDPOINT, "status", UserStatus.class);
        return statusResponse;
    }

}

package me.kecker.lichess4j.api;

import java.io.IOException;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;
import me.kecker.lichess4j.model.users.UserStatus;

public interface UsersService {

    UserStatus getRealTimeUserStatus() throws IllegalStatusCodeException, IOException,
            InterruptedException;

}

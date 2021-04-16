package me.kecker.lichess4j.api;

import java.io.IOException;
import me.kecker.lichess4j.http.exceptions.IllegalStatusCodeException;

public interface UsersService {

    String getRealTimeUserStatus() throws IllegalStatusCodeException, IOException,
            InterruptedException;

}

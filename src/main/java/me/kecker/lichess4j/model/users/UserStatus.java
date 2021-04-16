package me.kecker.lichess4j.model.users;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import me.kecker.lichess4j.model.enums.Title;

// don't use @Value to allow getter customization
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserStatus {
    @Getter
    private String id;
    @Getter
    private String name;

    // the following values will be null for some users
    private Title title;
    private Boolean online;
    private Boolean playing;
    private Boolean streaming;
    private Boolean patron;

    public UserStatus(String id, String name) {
        this(id, name, null, null, null, null, null);
    }

    public Optional<Title> getTitle() {
        return Optional.ofNullable(title);
    }

    public Optional<Boolean> getOnline() {
        return Optional.ofNullable(online);
    }

    public Optional<Boolean> getPlaying() {
        return Optional.ofNullable(playing);
    }

    public Optional<Boolean> getStreaming() {
        return Optional.ofNullable(streaming);
    }

    public Optional<Boolean> getPatron() {
        return Optional.ofNullable(patron);
    }

}

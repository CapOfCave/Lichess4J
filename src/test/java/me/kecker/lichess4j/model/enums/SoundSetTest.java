package me.kecker.lichess4j.model.enums;

import java.util.List;
import me.kecker.lichess4j.test.utils.EnumGsonMappingTestUtils;
import org.junit.Test;

public class SoundSetTest {

    // taken from https://lichess.org/api#operation/account
    private static final List<String> EXPECTED_NAMES = List.of("silent", "standard", "piano", "nes",
            "sfx", "futuristic", "robot", "music", "speech");

    @Test
    public void serialize_happyDay_returnsCorrectValues() {
        EnumGsonMappingTestUtils.assertEnumsAreCorrectlySerialized(EXPECTED_NAMES, SoundSet
                .values());
    }

}

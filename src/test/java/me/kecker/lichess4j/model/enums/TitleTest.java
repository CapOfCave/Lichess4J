package me.kecker.lichess4j.model.enums;

import java.util.List;
import me.kecker.lichess4j.test.utils.EnumGsonMappingTestUtils;
import org.junit.Test;

public class TitleTest {

    // taken from https://lichess.org/api#operation/account
    private static final List<String> EXPECTED_NAMES = List.of("GM", "WGM", "IM", "WIM", "FM",
            "WFM", "NM", "CM", "WCM", "WNM", "LM", "BOT");

    @Test
    public void serialize_happyDay_returnsCorrectValues() {
        EnumGsonMappingTestUtils.assertEnumsAreCorrectlySerialized(EXPECTED_NAMES, Title.values());
    }

}

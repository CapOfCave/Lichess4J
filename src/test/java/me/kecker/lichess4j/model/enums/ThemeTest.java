package me.kecker.lichess4j.model.enums;

import java.util.List;
import me.kecker.lichess4j.test.utils.EnumGsonMappingTestUtils;
import org.junit.Test;

public class ThemeTest {

    // taken from https://lichess.org/api#operation/account
    private static final List<String> EXPECTED_NAMES = List.of("blue", "blue2", "blue3",
            "blue-marble", "canvas", "wood", "wood2", "wood3", "wood4", "maple", "maple2", "brown",
            "leather", "green", "marble", "green-plastic", "grey", "metal", "olive", "newspaper",
            "purple", "purple-diag", "pink", "ic");

    @Test
    public void serialize_happyDay_returnsCorrectValues() {
        EnumGsonMappingTestUtils.assertEnumsAreCorrectlySerialized(EXPECTED_NAMES, Theme.values());
    }
}

package me.kecker.lichess4j.model.enums;

import java.util.List;
import me.kecker.lichess4j.test.utils.EnumGsonMappingTestUtils;
import org.junit.Test;

public class PieceSet3dTest {

    // taken from https://lichess.org/api#operation/account
    private static final List<String> EXPECTED_NAMES = List.of("Basic", "Wood", "Metal", "RedVBlue",
            "ModernJade", "ModernWood", "Glass", "Trimmed", "Experimental", "Staunton",
            "CubesAndPi");

    @Test
    public void serialize_happyDay_returnsCorrectValues() {
        EnumGsonMappingTestUtils.assertEnumsAreCorrectlySerialized(EXPECTED_NAMES, PieceSet3d
                .values());
    }

}

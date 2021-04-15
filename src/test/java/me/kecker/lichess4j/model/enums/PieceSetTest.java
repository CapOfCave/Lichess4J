package me.kecker.lichess4j.model.enums;

import java.util.List;
import me.kecker.lichess4j.test.utils.EnumGsonMappingTestUtils;
import org.junit.Test;

public class PieceSetTest {

    // taken from https://lichess.org/api#operation/account
    private static final List<String> EXPECTED_NAMES = List.of("cburnett", "merida", "alpha",
            "pirouetti", "chessnut", "chess7", "reillycraig", "companion", "riohacha", "kosal",
            "leipzig", "fantasy", "spatial", "california", "pixel", "maestro", "fresca", "cardinal",
            "gioco", "tatiana", "staunty", "governor", "dubrovny", "icpieces", "shapes", "letter");

    @Test
    public void serialize_happyDay_returnsCorrectValues() {
        EnumGsonMappingTestUtils.assertEnumsAreCorrectlySerialized(EXPECTED_NAMES, PieceSet
                .values());
    }
}

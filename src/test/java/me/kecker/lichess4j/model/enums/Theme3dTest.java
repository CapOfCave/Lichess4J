package me.kecker.lichess4j.model.enums;

import java.util.List;
import me.kecker.lichess4j.test.utils.EnumGsonMappingTestUtils;
import org.junit.Test;

public class Theme3dTest {

    // taken from https://lichess.org/api#operation/account
    private static final List<String> EXPECTED_NAMES = List.of("Black-White-Aluminium",
            "Brushed-Aluminium", "China-Blue", "China-Green", "China-Grey", "China-Scarlet",
            "Classic-Blue", "Gold-Silver", "Light-Wood", "Power-Coated", "Rosewood", "Marble",
            "Wax", "Jade", "Woodi");

    @Test
    public void serialize_happyDay_returnsCorrectValues() {
        EnumGsonMappingTestUtils.assertEnumsAreCorrectlySerialized(EXPECTED_NAMES, Theme3d
                .values());
    }

}

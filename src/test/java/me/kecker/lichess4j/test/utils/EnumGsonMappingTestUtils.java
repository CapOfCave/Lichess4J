package me.kecker.lichess4j.test.utils;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumGsonMappingTestUtils {

    public static void assertEnumsAreCorrectlySerialized(List<String> expectedNames,
            Enum<?>[] values) {
        List<String> expectedJsons = expectedNames.stream()
                .map(s -> "\"" + s + "\"")
                .sorted() // for easily seeing differences
                .collect(Collectors.toList());

        Gson gson = new Gson();
        assertThat(Stream.of(values)
                .map(gson::toJson)
                .sorted() // for easily seeing differences
                .collect(Collectors.toList())).hasSameElementsAs(expectedJsons);
    }
    
    private EnumGsonMappingTestUtils() {
        // this class should not be instantiated
    }
}

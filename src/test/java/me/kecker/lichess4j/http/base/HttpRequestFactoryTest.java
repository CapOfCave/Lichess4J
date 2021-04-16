package me.kecker.lichess4j.http.base;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class HttpRequestFactoryTest {

    private static final String BEARER_TOKEN = "BEARER_TOKEN";
    private static final String ENDPOINT = "endpoint";
    private static final String PATH = "path";
    private static final String PATH_WITH_SLASH = "/" + PATH;
    private static final String BASE_URL = "https://lichess.org/api/";
    private static final String FULL_PATH = BASE_URL + ENDPOINT + "/" + PATH + "?";
    private static final Map<String, String> PARAMETERS = Map.of("a", "1", "b", "2");
    private static final Map<String, String> PARAMETERS_NOT_URL_ENCODED = Map.of("a", "+1&", "\\b/",
            "2");

    private HttpRequestFactory objectUnderTest;

    @Before
    public void setUp() {
        this.objectUnderTest = new HttpRequestFactory(BEARER_TOKEN);
    }

    @Test
    public void createURI_happyDay_returnsURI() {
        URI uri = this.objectUnderTest.createURI(ENDPOINT, PATH, Collections.emptyMap());
        assertThat(uri.toString()).isEqualTo(FULL_PATH);
    }

    @Test
    public void createURI_pathWithSlash_returnsURI() {
        URI uri = this.objectUnderTest.createURI(ENDPOINT, PATH_WITH_SLASH, Collections.emptyMap());
        assertThat(uri.toString()).isEqualTo(FULL_PATH);
    }

    @Test
    public void createURI_withParameter_returnsURI() {
        URI uri = this.objectUnderTest.createURI(ENDPOINT, PATH_WITH_SLASH, PARAMETERS);
        assertThat(uri.toString()).matches(
                "https\\:\\/\\/lichess\\.org\\/api\\/endpoint\\/path\\?((a|b)=(1|2))&((a|b)=(1|2))");
        assertThat(uri.toString()).contains("a=1");
        assertThat(uri.toString()).contains("b=2");
    }

    @Test
    public void createURI_withInvalidUrlParameter_returnsEnodedURI() {
        URI uri = this.objectUnderTest.createURI(ENDPOINT, PATH_WITH_SLASH,
                PARAMETERS_NOT_URL_ENCODED);
        assertThat(uri.toString()).matches(
                "https\\:\\/\\/lichess\\.org\\/api\\/endpoint\\/path\\?((a|%5Cb%2F)=(%2B1%26|2))&((a|%5Cb%2F)=(%2B1%26|2))");
        assertThat(uri.toString()).contains("a=%2B1%26");
        assertThat(uri.toString()).contains("%5Cb%2F=2");
    }
    
    @Test
    public void createURI_pathIsNull_returnsURI() {
        URI uri = this.objectUnderTest.createURI(ENDPOINT, null, Collections.emptyMap());
        assertThat(uri.toString()).isEqualTo(BASE_URL + ENDPOINT + "?");
    }

    @Test
    public void createGetRequest_happyDay_returnsCorrectRequest() {
        HttpRequest request = this.objectUnderTest.createGetRequest(ENDPOINT, PATH, Collections
                .emptyMap());
        assertThat(request.headers()
                .allValues("Authorization")).isEqualTo(List.of("Bearer " + BEARER_TOKEN));
        assertThat(request.headers()
                .map()
                .size()).isEqualTo(1);
        assertThat(request.method()).isEqualTo("GET");
        assertThat(request.uri()
                .toString()).isEqualTo(FULL_PATH);
    }

    @Test
    public void createGetRequest_pathIsNull_returnsCorrectRequest() {
        HttpRequest request = this.objectUnderTest.createGetRequest(ENDPOINT, null, Collections
                .emptyMap());
        assertThat(request.headers()
                .allValues("Authorization")).isEqualTo(List.of("Bearer " + BEARER_TOKEN));
        assertThat(request.headers()
                .map()
                .size()).isEqualTo(1);
        assertThat(request.method()).isEqualTo("GET");
        assertThat(request.uri()
                .toString()).isEqualTo(BASE_URL + ENDPOINT + "?");
    }
}

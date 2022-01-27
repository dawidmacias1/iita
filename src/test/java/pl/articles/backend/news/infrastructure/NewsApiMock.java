package pl.articles.backend.news.infrastructure;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class NewsApiMock {

    private static final String URL = "/news?country=pl&category=business&apiKey=xxx";

    public static void setupValidMockNewsResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo(URL))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        NewsApiMock.class.getClassLoader().getResourceAsStream("body/news_api_response.json"),
                                        defaultCharset()))));
    }

    public static void setupBadRequestMockNewsResponse(WireMockServer mockService) {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo(URL))
                .willReturn(WireMock.aResponse().withStatus(HttpStatus.BAD_REQUEST.value())));
    }

    public static void setupUnauthorizedMockNewsResponse(WireMockServer mockService) {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo(URL))
                .willReturn(WireMock.aResponse().withStatus(HttpStatus.UNAUTHORIZED.value())));
    }

    public static void setupTooManyRequestsMockNewsResponse(WireMockServer mockService) {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo(URL))
                .willReturn(WireMock.aResponse().withStatus(HttpStatus.TOO_MANY_REQUESTS.value())));
    }

    public static void setupInternalServerErrorMockNewsResponse(WireMockServer mockService) {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo(URL))
                .willReturn(WireMock.aResponse().withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())));
    }
}

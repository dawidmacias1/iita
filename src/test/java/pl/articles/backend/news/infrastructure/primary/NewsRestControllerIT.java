package pl.articles.backend.news.infrastructure.primary;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.articles.backend.news.infrastructure.NewsApiMock;
import pl.articles.backend.news.infrastructure.WireMockConfig;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = AFTER_CLASS)
class NewsRestControllerIT {

    @Autowired
    private WireMockServer wireMockServer;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void shouldReturnValidResponseIfParametersAreValid() throws IOException {
        //given
        String country = "pl";
        String category = "business";
        NewsApiMock.setupValidMockNewsResponse(wireMockServer);
        Response response = RestAssured
                .with()
                .when()
                .request()
                .get("/news/" + country + "/" + category);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody().print()).isNotBlank();
    }

    @Test
    void shouldReturnErrorResponseIfCountryIsInvalid() throws IOException {
        //given
        String country = "poland";
        String category = "business";
        NewsApiMock.setupValidMockNewsResponse(wireMockServer);
        Response response = RestAssured
                .with()
                .when()
                .request()
                .get("/news/" + country + "/" + category);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(400);
        assertThat(response.getBody().print()).isNotBlank();
    }
}

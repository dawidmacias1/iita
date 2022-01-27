package pl.articles.backend.news.infrastructure.secondary;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.articles.backend.news.application.domain.core.Article;
import pl.articles.backend.news.boundary.secondary.NewsClient;
import pl.articles.backend.news.infrastructure.NewsApiMock;
import pl.articles.backend.news.infrastructure.WireMockConfig;
import pl.articles.backend.news.infrastructure.secondary.exception.NewsApiBadRequestException;
import pl.articles.backend.news.infrastructure.secondary.exception.NewsApiInternalServerException;
import pl.articles.backend.news.infrastructure.secondary.exception.NewsApiTooManyRequestsException;
import pl.articles.backend.news.infrastructure.secondary.exception.NewsApiUnauthorizedException;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
@DirtiesContext(classMode = AFTER_CLASS)
class NewsRestClientIT {

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private NewsClient newsClient;

    @Test
    void getNewsByCountryAndCategory_shouldReturnListOfArticleIfCountryAndCategoryAreValid() throws IOException {
        //given
        String country = "pl";
        String category = "business";
        NewsApiMock.setupValidMockNewsResponse(wireMockServer);

        //when
        List<Article> result = newsClient.getNewsByCountryAndCategory(country, category);

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isPositive();
    }

    @Test
    void getNewsByCountryAndCategory_shouldThrowExceptionIfApiReturnHttpCode400() {
        //given
        String country = "pl";
        String category = "business";
        NewsApiMock.setupBadRequestMockNewsResponse(wireMockServer);

        //when
        //then
        assertThatThrownBy(() -> newsClient.getNewsByCountryAndCategory(country, category))
                .isInstanceOf(NewsApiBadRequestException.class);
    }

    @Test
    void getNewsByCountryAndCategory_shouldThrowExceptionIfApiReturnHttpCode401() {
        //given
        String country = "pl";
        String category = "business";
        NewsApiMock.setupUnauthorizedMockNewsResponse(wireMockServer);

        //when
        //then
        assertThatThrownBy(() -> newsClient.getNewsByCountryAndCategory(country, category))
                .isInstanceOf(NewsApiUnauthorizedException.class);
    }

    @Test
    void getNewsByCountryAndCategory_shouldThrowExceptionIfApiReturnHttpCode429() {
        //given
        String country = "pl";
        String category = "business";
        NewsApiMock.setupTooManyRequestsMockNewsResponse(wireMockServer);

        //when
        //then
        assertThatThrownBy(() -> newsClient.getNewsByCountryAndCategory(country, category))
                .isInstanceOf(NewsApiTooManyRequestsException.class);
    }

    @Test
    void getNewsByCountryAndCategory_shouldThrowExceptionIfApiReturnHttpCode500() {
        //given
        String country = "pl";
        String category = "business";
        NewsApiMock.setupInternalServerErrorMockNewsResponse(wireMockServer);

        //when
        //then
        assertThatThrownBy(() -> newsClient.getNewsByCountryAndCategory(country, category))
                .isInstanceOf(NewsApiInternalServerException.class);
    }
}

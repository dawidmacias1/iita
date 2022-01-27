package pl.articles.backend.news.application.domain;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.articles.backend.news.application.ISO3166Validator;
import pl.articles.backend.news.application.domain.core.Article;
import pl.articles.backend.news.application.domain.core.News;
import pl.articles.backend.news.application.domain.exception.CategoryMissingException;
import pl.articles.backend.news.application.domain.exception.CountryCodeMissingException;
import pl.articles.backend.news.application.domain.exception.InvalidISO3166CodeException;
import pl.articles.backend.news.boundary.secondary.NewsClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@SpringBootTest
class NewsServiceTests {

    private static final MockedStatic<ISO3166Validator> isoValidatorMock = mockStatic(ISO3166Validator.class);

    @Autowired
    private NewsService newsService;

    @MockBean
    private NewsClient newsClient;

    @AfterAll
    public static void close() {
        isoValidatorMock.close();
    }

    @Test
    void getNewsByCountryAndCategory_shouldReturnNewsAndArticlesSortedByNewestIfCountryAndCategoryAreValid() {
        //given
        String country = "pl";
        String category = "business";
        isoValidatorMock.when(() -> ISO3166Validator.validate(country)).thenReturn(true);
        when(newsClient.getNewsByCountryAndCategory(country, category)).thenReturn(prepareStubListOfCorrectArticle());

        //when
        News result = newsService.getNewsByCountryAndCategory(country, category);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getCategory()).isEqualTo(category);
        assertThat(result.getCountry()).isEqualTo(country);
        assertThat(result.getArticles()).isNotNull();
        assertThat(result.getArticles().size()).isPositive();
        assertThat(result.getArticles().get(0).getDate()).isAfter(result.getArticles().get(1).getDate());
    }

    private List<Article> prepareStubListOfCorrectArticle() {
        Article article1 = Article.builder()
                .sourceName("test")
                .description("test1")
                .title("test2")
                .author("test3")
                .date(LocalDate.now().minusDays(1))
                .build();
        Article article2 = Article.builder()
                .sourceName("test4")
                .description("test5")
                .title("test6")
                .author("test7")
                .date(LocalDate.now())
                .build();
        return new ArrayList<>(List.of(article1, article2));
    }

    @Test
    void getNewsByCountryAndCategory_shouldThrowExceptionIfCountryIsNull() {
        //given
        String country = null;
        String category = "business";

        //when
        //then
        assertThatThrownBy(() -> newsService.getNewsByCountryAndCategory(country, category))
                .isInstanceOf(CountryCodeMissingException.class);
    }

    @Test
    void getNewsByCountryAndCategory_shouldThrowExceptionIfCategoryIsNull() {
        //given
        String country = "pl";
        String category = null;

        //when
        //then
        assertThatThrownBy(() -> newsService.getNewsByCountryAndCategory(country, category))
                .isInstanceOf(CategoryMissingException.class);
    }

    @Test
    void getNewsByCountryAndCategory_shouldThrowExceptionIfCountryIsInvalid() {
        //given
        String country = "poland";
        String category = "business";
        isoValidatorMock.when(() -> ISO3166Validator.validate(country)).thenReturn(false);

        //when
        //then
        assertThatThrownBy(() -> newsService.getNewsByCountryAndCategory(country, category))
                .isInstanceOf(InvalidISO3166CodeException.class);
    }
}

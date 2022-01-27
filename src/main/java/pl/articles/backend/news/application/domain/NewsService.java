package pl.articles.backend.news.application.domain;

import lombok.RequiredArgsConstructor;
import pl.articles.backend.news.application.ISO3166Validator;
import pl.articles.backend.news.boundary.secondary.NewsClient;
import pl.articles.backend.news.application.domain.core.News;
import pl.articles.backend.news.application.domain.exception.CategoryMissingException;
import pl.articles.backend.news.application.domain.exception.CountryCodeMissingException;
import pl.articles.backend.news.application.domain.exception.InvalidISO3166CodeException;

import java.util.Optional;

@RequiredArgsConstructor
public class NewsService {

    private final NewsClient newsClient;

    public News getNewsByCountryAndCategory(String country, String category) {
        var countryOpt = Optional.ofNullable(country).orElseThrow(CountryCodeMissingException::new);
        var categoryOpt = Optional.ofNullable(category).orElseThrow(CategoryMissingException::new);
        if (!ISO3166Validator.validate(countryOpt)) {
            throw new InvalidISO3166CodeException();
        }
        var articles = newsClient.getNewsByCountryAndCategory(countryOpt, categoryOpt);
        articles.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        return News.builder()
                .country(countryOpt)
                .category(categoryOpt)
                .articles(articles)
                .build();
    }
}

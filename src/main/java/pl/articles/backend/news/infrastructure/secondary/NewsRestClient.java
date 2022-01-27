package pl.articles.backend.news.infrastructure.secondary;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import pl.articles.backend.news.application.domain.core.Article;
import pl.articles.backend.news.boundary.secondary.NewsClient;
import pl.articles.backend.news.infrastructure.secondary.dto.ExternalArticle;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class NewsRestClient implements NewsClient {

    private final NewsFeignClient newsFeignClient;

    @Value("${news-api.apikey}")
    private String apiKey;

    @Override
    public List<Article> getNewsByCountryAndCategory(String country, String category) {
        return newsFeignClient.getNews(country, category, apiKey)
                .getArticles()
                .stream()
                .map(ExternalArticle::toArticle)
                .collect(Collectors.toList());
    }
}

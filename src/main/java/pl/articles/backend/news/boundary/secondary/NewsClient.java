package pl.articles.backend.news.boundary.secondary;

import pl.articles.backend.news.application.domain.core.Article;

import java.util.List;

public interface NewsClient {

    List<Article> getNewsByCountryAndCategory(String country, String category);
}

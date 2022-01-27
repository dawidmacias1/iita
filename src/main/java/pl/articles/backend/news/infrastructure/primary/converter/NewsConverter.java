package pl.articles.backend.news.infrastructure.primary.converter;

import pl.articles.backend.news.infrastructure.primary.dto.Article;
import pl.articles.backend.news.infrastructure.primary.dto.NewsQueryResponse;
import pl.articles.backend.news.application.domain.core.News;

import java.util.stream.Collectors;

public class NewsConverter {

    private NewsConverter() {
        throw new IllegalStateException("This class should not be instantiated!");
    }

    public static NewsQueryResponse convert(News news) {
        return NewsQueryResponse.builder()
                .category(news.getCategory())
                .country(news.getCountry())
                .articles(news.getArticles()
                        .stream()
                        .map(NewsConverter::buildArticle)
                        .collect(Collectors.toList()))
                .build();
    }

    private static Article buildArticle(pl.articles.backend.news.application.domain.core.Article article) {
        return Article.builder()
                .articleUrl(article.getArticleUrl())
                .author(article.getAuthor())
                .date(article.getDate())
                .description(article.getDescription())
                .imageUrl(article.getImageUrl())
                .sourceName(article.getSourceName())
                .title(article.getTitle())
                .build();
    }
}

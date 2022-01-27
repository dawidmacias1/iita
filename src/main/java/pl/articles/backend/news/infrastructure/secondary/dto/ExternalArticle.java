package pl.articles.backend.news.infrastructure.secondary.dto;

import lombok.Getter;
import pl.articles.backend.news.application.domain.core.Article;

import java.time.LocalDate;

@Getter
public class ExternalArticle {

    private String author;
    private String title;
    private String description;
    private ExternalSource source;
    private String url;
    private String urlToImage;
    private LocalDate publishedAt;

    public Article toArticle() {
        return Article.builder()
                .author(author)
                .title(title)
                .description(description)
                .date(publishedAt)
                .sourceName(source.getName())
                .articleUrl(url)
                .imageUrl(urlToImage)
                .build();
    }
}

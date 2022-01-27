package pl.articles.backend.news.application.domain.core;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class News {

    String country;
    String category;
    List<Article> articles;
}

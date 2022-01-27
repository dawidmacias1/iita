package pl.articles.backend.news.application.domain.core;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Article {

    String author;
    String title;
    String description;
    LocalDate date;
    String sourceName;
    String articleUrl;
    String imageUrl;
}

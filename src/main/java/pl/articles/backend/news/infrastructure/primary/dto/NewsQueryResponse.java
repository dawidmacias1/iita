package pl.articles.backend.news.infrastructure.primary.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class NewsQueryResponse {

    String country;
    String category;
    List<Article> articles;
}

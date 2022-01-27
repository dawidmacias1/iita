package pl.articles.backend.news.infrastructure.secondary.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ExternalNews {

    private List<ExternalArticle> articles;
}

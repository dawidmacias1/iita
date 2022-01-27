package pl.articles.backend.news.infrastructure.secondary.exception;

public class NewsApiUnauthorizedException extends RuntimeException {

    private static final String MESSAGE = "News API - Unauthorized!";

    public NewsApiUnauthorizedException() {
        super(MESSAGE);
    }
}

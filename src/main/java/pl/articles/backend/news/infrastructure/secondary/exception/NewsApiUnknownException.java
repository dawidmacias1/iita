package pl.articles.backend.news.infrastructure.secondary.exception;

public class NewsApiUnknownException extends RuntimeException {

    private static final String MESSAGE = "News API - Unknown Error!";

    public NewsApiUnknownException() {
        super(MESSAGE);
    }
}

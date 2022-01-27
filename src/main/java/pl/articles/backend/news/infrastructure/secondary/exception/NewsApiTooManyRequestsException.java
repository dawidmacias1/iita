package pl.articles.backend.news.infrastructure.secondary.exception;

public class NewsApiTooManyRequestsException extends RuntimeException {

    private static final String MESSAGE = "News API - Too Many Requests!";

    public NewsApiTooManyRequestsException() {
        super(MESSAGE);
    }
}

package pl.articles.backend.news.infrastructure.secondary.exception;

public class NewsApiInternalServerException extends RuntimeException {

    private static final String MESSAGE = "News API - Server Error!";

    public NewsApiInternalServerException() {
        super(MESSAGE);
    }
}

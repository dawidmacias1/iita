package pl.articles.backend.news.infrastructure.secondary.exception;

public class NewsApiBadRequestException extends RuntimeException {

    private static final String MESSAGE = "News API - Bad Request!";

    public NewsApiBadRequestException() {
        super(MESSAGE);
    }
}

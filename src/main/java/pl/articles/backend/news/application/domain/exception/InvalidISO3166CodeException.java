package pl.articles.backend.news.application.domain.exception;

public class InvalidISO3166CodeException extends RuntimeException {

    private static final String MESSAGE = "Invalid ISO3166 Code!";

    public InvalidISO3166CodeException() {
        super(MESSAGE);
    }
}

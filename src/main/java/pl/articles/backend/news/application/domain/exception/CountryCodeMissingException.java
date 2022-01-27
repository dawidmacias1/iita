package pl.articles.backend.news.application.domain.exception;

public class CountryCodeMissingException extends RuntimeException {

    private static final String MESSAGE = "Country code parameter is missing!";

    public CountryCodeMissingException() {
        super(MESSAGE);
    }
}

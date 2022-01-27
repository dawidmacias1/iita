package pl.articles.backend.news.application.domain.exception;

public class CategoryMissingException extends RuntimeException {

    private static final String MESSAGE = "Category parameter is missing!";

    public CategoryMissingException() {
        super(MESSAGE);
    }
}

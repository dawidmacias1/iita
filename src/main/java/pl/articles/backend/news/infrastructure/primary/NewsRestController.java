package pl.articles.backend.news.infrastructure.primary;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.articles.backend.news.application.domain.NewsService;
import pl.articles.backend.news.application.domain.exception.CategoryMissingException;
import pl.articles.backend.news.application.domain.exception.CountryCodeMissingException;
import pl.articles.backend.news.application.domain.exception.InvalidISO3166CodeException;
import pl.articles.backend.news.boundary.primary.NewsController;
import pl.articles.backend.news.infrastructure.primary.converter.NewsConverter;
import pl.articles.backend.news.infrastructure.primary.dto.Error;
import pl.articles.backend.news.infrastructure.primary.dto.NewsQueryResponse;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Slf4j
public class NewsRestController implements NewsController {

    private static final String GENERAL_ERROR_MESSAGE = "Internal Server Error!";

    private final NewsService newsService;

    @Override
    @GetMapping("{country}/{category}")
    public ResponseEntity<NewsQueryResponse> getNewsByCountryAndCategory(@PathVariable String country, @PathVariable String category) {
        return ResponseEntity.ok(NewsConverter.convert(newsService.getNewsByCountryAndCategory(country, category)));
    }

    @Override
    @ExceptionHandler({InvalidISO3166CodeException.class, CategoryMissingException.class, CountryCodeMissingException.class})
    public ResponseEntity<Error> handleBusinessException(RuntimeException runtimeException) {
        log.error(runtimeException.getMessage());
        return new ResponseEntity<>(new Error(runtimeException.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Error> handleApplicationException(RuntimeException runtimeException) {
        log.error(runtimeException.getMessage());
        return new ResponseEntity<>(new Error(GENERAL_ERROR_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

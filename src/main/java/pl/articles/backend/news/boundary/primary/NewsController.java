package pl.articles.backend.news.boundary.primary;

import org.springframework.http.ResponseEntity;
import pl.articles.backend.news.infrastructure.primary.dto.Error;
import pl.articles.backend.news.infrastructure.primary.dto.NewsQueryResponse;

public interface NewsController {

    ResponseEntity<NewsQueryResponse> getNewsByCountryAndCategory(String country, String category);
    ResponseEntity<Error> handleBusinessException(RuntimeException runtimeException);
    ResponseEntity<Error> handleApplicationException(RuntimeException runtimeException);
}

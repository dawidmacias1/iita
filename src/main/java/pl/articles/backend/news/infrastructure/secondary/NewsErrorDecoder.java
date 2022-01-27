package pl.articles.backend.news.infrastructure.secondary;

import feign.Response;
import feign.codec.ErrorDecoder;
import pl.articles.backend.news.infrastructure.secondary.exception.NewsApiBadRequestException;
import pl.articles.backend.news.infrastructure.secondary.exception.NewsApiInternalServerException;
import pl.articles.backend.news.infrastructure.secondary.exception.NewsApiTooManyRequestsException;
import pl.articles.backend.news.infrastructure.secondary.exception.NewsApiUnauthorizedException;
import pl.articles.backend.news.infrastructure.secondary.exception.NewsApiUnknownException;

public class NewsErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 400:
                return new NewsApiBadRequestException();
            case 401:
                return new NewsApiUnauthorizedException();
            case 429:
                return new NewsApiTooManyRequestsException();
            case 500:
                return new NewsApiInternalServerException();
            default:
                return new NewsApiUnknownException();
        }
    }
}

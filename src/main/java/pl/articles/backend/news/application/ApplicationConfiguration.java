package pl.articles.backend.news.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.articles.backend.news.boundary.secondary.NewsClient;
import pl.articles.backend.news.application.domain.NewsService;

@Configuration
public class ApplicationConfiguration {

    @Bean
    NewsService newsService(NewsClient newsClient) {
        return new NewsService(newsClient);
    }
}

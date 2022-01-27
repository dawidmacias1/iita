package pl.articles.backend.news.infrastructure.secondary;

import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.articles.backend.news.boundary.secondary.NewsClient;

@EnableFeignClients
@Configuration
public class NewsConfiguration {

    @Bean
    NewsClient newsClient(NewsFeignClient newsFeignClient) {
        return new NewsRestClient(newsFeignClient);
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return new NewsErrorDecoder();
    }
}

package pl.articles.backend.news.infrastructure.secondary;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.articles.backend.news.infrastructure.secondary.dto.ExternalNews;

@FeignClient(value = "news", url = "${news-api.url}")
public interface NewsFeignClient {

    @GetMapping
    ExternalNews getNews(@RequestParam("country") String country,
                         @RequestParam("category") String category,
                         @RequestParam("apiKey") String apiKey);
}

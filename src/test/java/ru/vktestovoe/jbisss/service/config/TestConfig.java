package ru.vktestovoe.jbisss.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.service.api.ApiHttpRequestHandlerService;
import ru.vktestovoe.jbisss.service.AlbumService;
import ru.vktestovoe.jbisss.service.CommentService;
import ru.vktestovoe.jbisss.service.PostService;
import ru.vktestovoe.jbisss.service.UserService;

@Configuration
public class TestConfig {

    @Bean
    public ApiHttpRequestHandlerService requestHandlerService(RestTemplate restTemplate) {
        return new ApiHttpRequestHandlerService(restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommentService commentService(ApiHttpRequestHandlerService apiHttpRequestHandlerService) {
        return new CommentService(apiHttpRequestHandlerService);
    }

    @Bean
    public PostService postService(ApiHttpRequestHandlerService apiHttpRequestHandlerService, CommentService commentService) {
        return new PostService(apiHttpRequestHandlerService, commentService);
    }

    @Bean
    public AlbumService albumService(ApiHttpRequestHandlerService apiHttpRequestHandlerService) {
        return new AlbumService(apiHttpRequestHandlerService);
    }

    @Bean
    public UserService userService(ApiHttpRequestHandlerService apiHttpRequestHandlerService) {
        return new UserService(apiHttpRequestHandlerService);
    }
}

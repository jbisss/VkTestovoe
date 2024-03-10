package ru.vktestovoe.jbisss.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.service.*;

@Configuration
public class TestConfig {

    @Bean
    public RequestHandlerService requestHandlerService(RestTemplate restTemplate) {
        return new RequestHandlerService(restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommentService commentService(RestTemplate restTemplate) {
        return new CommentService(restTemplate);
    }

    @Bean
    public PostService postService(RequestHandlerService requestHandlerService, RestTemplate restTemplate, CommentService commentService) {
        return new PostService(requestHandlerService, restTemplate, commentService);
    }

    @Bean
    public AlbumService albumService(RequestHandlerService requestHandlerService, RestTemplate restTemplate) {
        return new AlbumService(requestHandlerService, restTemplate);
    }

    @Bean
    public UserService userService(RequestHandlerService requestHandlerService, RestTemplate restTemplate) {
        return new UserService(requestHandlerService, restTemplate);
    }
}

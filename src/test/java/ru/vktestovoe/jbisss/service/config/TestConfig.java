package ru.vktestovoe.jbisss.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.service.RequestHandlerService;
import ru.vktestovoe.jbisss.service.AlbumService;
import ru.vktestovoe.jbisss.service.CommentService;
import ru.vktestovoe.jbisss.service.PostService;
import ru.vktestovoe.jbisss.service.UserService;

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
    public CommentService commentService(RequestHandlerService requestHandlerService) {
        return new CommentService(requestHandlerService);
    }

    @Bean
    public PostService postService(RequestHandlerService requestHandlerService, CommentService commentService) {
        return new PostService(requestHandlerService, commentService);
    }

    @Bean
    public AlbumService albumService(RequestHandlerService requestHandlerService) {
        return new AlbumService(requestHandlerService);
    }

    @Bean
    public UserService userService(RequestHandlerService requestHandlerService) {
        return new UserService(requestHandlerService);
    }
}

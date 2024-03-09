package ru.vktestovoe.jbisss.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.service.AlbumService;
import ru.vktestovoe.jbisss.service.CommentService;
import ru.vktestovoe.jbisss.service.PostService;
import ru.vktestovoe.jbisss.service.UserService;

@Configuration
public class TestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommentService commentService(RestTemplate restTemplate) {
        return new CommentService(restTemplate);
    }

    @Bean
    public PostService postService(RestTemplate restTemplate, CommentService commentService) {
        return new PostService(restTemplate, commentService);
    }

    @Bean
    public AlbumService albumService(RestTemplate restTemplate) {
        return new AlbumService(restTemplate);
    }

    @Bean
    public UserService userService(RestTemplate restTemplate) {
        return new UserService(restTemplate);
    }
}

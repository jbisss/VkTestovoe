package ru.vktestovoe.jbisss.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.service.api.ApiCacheRequestHandlerService;
import ru.vktestovoe.jbisss.service.api.ApiHttpRequestHandlerService;
import ru.vktestovoe.jbisss.service.AlbumService;
import ru.vktestovoe.jbisss.service.CommentService;
import ru.vktestovoe.jbisss.service.PostService;
import ru.vktestovoe.jbisss.service.UserService;
import ru.vktestovoe.jbisss.service.api.ApiRequestService;

@Configuration
public class TestConfig {

    @Bean
    public ApiRequestService apiRequestService(ApiCacheRequestHandlerService apiCacheRequestHandlerService, ApiHttpRequestHandlerService requestHandlerService) {
        return new ApiRequestService(apiCacheRequestHandlerService, requestHandlerService);
    }

    @Bean
    public ApiCacheRequestHandlerService apiCacheRequestHandlerService() {
        return new ApiCacheRequestHandlerService();
    }

    @Bean
    public ApiHttpRequestHandlerService requestHandlerService(RestTemplate restTemplate) {
        return new ApiHttpRequestHandlerService(restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommentService commentService(ApiRequestService apiRequestService) {
        return new CommentService(apiRequestService);
    }

    @Bean
    public PostService postService(ApiRequestService apiRequestService, CommentService commentService) {
        return new PostService(apiRequestService, commentService);
    }

    @Bean
    public AlbumService albumService(ApiRequestService apiRequestService) {
        return new AlbumService(apiRequestService);
    }

    @Bean
    public UserService userService(ApiRequestService apiRequestService) {
        return new UserService(apiRequestService);
    }
}

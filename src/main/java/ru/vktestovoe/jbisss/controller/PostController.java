package ru.vktestovoe.jbisss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.PostDto;
import ru.vktestovoe.jbisss.service.PostService;

@RestController
@RequestMapping(ApplicationConstants.Url.API_URL + ApplicationConstants.Url.POST_URL)
public class PostController extends BaseController<PostDto> {

    public PostController(PostService postService) {
        super(postService);
    }
}

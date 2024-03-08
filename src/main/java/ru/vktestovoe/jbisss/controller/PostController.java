package ru.vktestovoe.jbisss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vktestovoe.jbisss.dto.PostDto;
import ru.vktestovoe.jbisss.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController extends BaseController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<PostDto> getPosts() {
        return postService.getPosts();
    }
}

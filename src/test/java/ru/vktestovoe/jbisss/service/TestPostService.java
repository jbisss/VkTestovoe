package ru.vktestovoe.jbisss.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.dto.CommentDto;
import ru.vktestovoe.jbisss.dto.PostDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestPostService {

    @Test
    public void testGettingAllPosts() {
        RestTemplate restTemplate = new RestTemplate();

        CommentService commentService = new CommentService(restTemplate);
        PostService postService = new PostService(commentService, restTemplate);
        List<PostDto> posts = postService.getPosts();

        System.out.println(posts);

        assertNotNull(posts);
    }

    @Test
    public void testGettingExactPost() {
        RestTemplate restTemplate = new RestTemplate();

        CommentService commentService = new CommentService(restTemplate);
        PostService postService = new PostService(commentService, restTemplate);
        PostDto post = postService.getPost("1");

        System.out.println(post);

        assertNotNull(post);
    }

    @Test
    public void testGettingPostComments() {
        RestTemplate restTemplate = new RestTemplate();

        CommentService commentService = new CommentService(restTemplate);
        PostService postService = new PostService(commentService, restTemplate);
        List<CommentDto> postComments = postService.getPostComments("1");

        System.out.println(postComments);

        assertNotNull(postComments);
    }

    @Test
    public void test() {

    }
}

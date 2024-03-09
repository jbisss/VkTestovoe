package ru.vktestovoe.jbisss.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vktestovoe.jbisss.dto.CommentDto;
import ru.vktestovoe.jbisss.dto.PostDto;
import ru.vktestovoe.jbisss.service.config.TestConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestPostService {

    private static final AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(TestConfig.class);

    private static final PostService postService = appContext.getBean(PostService.class);

    @Test
    public void testGetPostList() {
        List<PostDto> posts = postService.getList();

        assertNotNull(posts);
    }

    @Test
    public void testGetPost() {
        PostDto post = postService.get("1");

        assertNotNull(post);
    }

    @Test
    public void testGetPostComments() {
        List<CommentDto> postComments = postService.getPostComments("1");

        assertNotNull(postComments);
    }

    @Test
    public void testPostPost() {
        PostDto testPostDto = new PostDto();
        testPostDto.setId("101");
        testPostDto.setUserId("1");
        testPostDto.setBody("TestBody");
        testPostDto.setTitle("Test title");

        PostDto postedPost = postService.post(testPostDto);

        assertEquals(testPostDto, postedPost);
    }

    @Test
    public void testPutPost() {
        PostDto testPostDto = new PostDto();
        testPostDto.setId("1");
        testPostDto.setUserId("1");
        testPostDto.setBody("TestBody");
        testPostDto.setTitle("Test title");

        PostDto putPost = postService.put("1", testPostDto);

        assertEquals(testPostDto, putPost);
    }

    @Test
    public void testDeletePost() {
        String status = postService.delete("1");

        assertEquals("200 OK", status);
    }
}

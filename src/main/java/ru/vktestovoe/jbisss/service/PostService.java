package ru.vktestovoe.jbisss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.CommentDto;
import ru.vktestovoe.jbisss.dto.PostDto;

import java.util.List;

@Service
public class PostService extends BaseService<PostDto> {

    private final CommentService commentService;

    public PostService(RequestHandlerService requestHandlerService, RestTemplate restTemplate, CommentService commentService) {
        super(requestHandlerService, restTemplate, ApplicationConstants.Url.POST_URL);
        this.commentService = commentService;
    }

    public List<CommentDto> getPostComments(String postId) {
        return commentService.getCommentsByPostId(postId);
    }
}

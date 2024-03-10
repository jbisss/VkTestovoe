package ru.vktestovoe.jbisss.service;

import org.springframework.stereotype.Service;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.CommentDto;
import ru.vktestovoe.jbisss.dto.PostDto;

import java.util.List;

@Service
public class PostService extends BaseService<PostDto> {

    private final CommentService commentService;

    public PostService(RequestHandlerService requestHandlerService, CommentService commentService) {
        super(requestHandlerService, ApplicationConstants.Url.POST_URL);
        this.commentService = commentService;
    }

    public List<CommentDto> getPostComments(String postId) {
        return commentService.getCommentsByPostId(postId);
    }
}

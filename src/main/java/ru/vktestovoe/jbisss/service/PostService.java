package ru.vktestovoe.jbisss.service;

import org.springframework.stereotype.Service;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.CommentDto;
import ru.vktestovoe.jbisss.dto.PostDto;
import ru.vktestovoe.jbisss.service.api.ApiHttpRequestHandlerService;
import ru.vktestovoe.jbisss.service.api.ApiRequestService;

import java.util.List;

@Service
public class PostService extends BaseService<PostDto> {

    private final CommentService commentService;

    public PostService(ApiRequestService apiRequestService, CommentService commentService) {
        super(apiRequestService, ApplicationConstants.Url.POST_URL);
        this.commentService = commentService;
    }

    public List<CommentDto> getPostComments(String postId) {
        return commentService.getCommentsByPostId(postId);
    }
}

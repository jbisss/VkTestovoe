package ru.vktestovoe.jbisss.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.CommentDto;
import ru.vktestovoe.jbisss.service.api.ApiHttpRequestHandlerService;

import java.util.List;

@Service
public class CommentService extends BaseService<CommentDto>{

    public CommentService(ApiHttpRequestHandlerService apiHttpRequestHandlerService) {
        super(apiHttpRequestHandlerService, ApplicationConstants.Url.COMMENTS_URL);
    }

    @SuppressWarnings("unchecked")
    public List<CommentDto> getCommentsByPostId(String postId) {
        final String entireUrl = serviceUrl + ApplicationConstants.SLASH + postId;
        ParameterizedTypeReference<List<CommentDto>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<?> response = apiHttpRequestHandlerService.handleRequest(entireUrl, HttpMethod.GET, null, responseType);

        return (List<CommentDto>) response.getBody();
    }
}

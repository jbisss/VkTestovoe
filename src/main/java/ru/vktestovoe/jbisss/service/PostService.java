package ru.vktestovoe.jbisss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.CommentDto;
import ru.vktestovoe.jbisss.dto.PostDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService extends JsonPlaceHolderService {

    private static final String POST_URL = JSON_PLACE_HOLDER_URL + "/posts";

    private final CommentService commentService;
    private final RestTemplate restTemplate;

    public List<PostDto> getPosts() {
        ParameterizedTypeReference<List<PostDto>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<PostDto>> response = restTemplate.exchange(POST_URL, HttpMethod.GET, null, responseType);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }
        return response.getBody();
    }

    public PostDto getPost(String postId) {
        final String POST_BY_ID_URL = POST_URL + ApplicationConstants.SLASH + postId;

        ResponseEntity<PostDto> response = restTemplate.getForEntity(POST_BY_ID_URL, PostDto.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }
        return response.getBody();
    }

    public List<CommentDto> getPostComments(String postId) {
        return commentService.getCommentsByPostId(postId);
    }
}

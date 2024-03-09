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

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final RestTemplate restTemplate;

    public List<CommentDto> getCommentsByPostId(String postId) {
        final String entireUrl = ApplicationConstants.Url.JSON_PLACE_HOLDER_URL + ApplicationConstants.Url.COMMENTS_URL;

        ParameterizedTypeReference<List<CommentDto>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<CommentDto>> response = restTemplate.exchange(entireUrl, HttpMethod.GET, null, responseType, postId);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }
        return response.getBody();
    }
}

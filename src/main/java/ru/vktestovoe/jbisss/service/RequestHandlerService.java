package ru.vktestovoe.jbisss.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RequestHandlerService {

    private final RestTemplate restTemplate;

    @Nonnull
    public ResponseEntity<?> handleRequest(@Nonnull String url, @Nonnull HttpMethod httpMethod, @Nullable HttpEntity<?> requestEntity, @Nonnull Class<?> type) {
        ResponseEntity<?> response = restTemplate.exchange(url, httpMethod, requestEntity, type);

        if (response.getStatusCode() != HttpStatus.OK || response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }
        return response;
    }

    @Nonnull
    public ResponseEntity<?> handleRequest(@Nonnull String url, @Nonnull HttpMethod httpMethod, @Nullable HttpEntity<?> requestEntity, ParameterizedTypeReference<?> responseType) {
        ResponseEntity<?> response = restTemplate.exchange(url, httpMethod, requestEntity, responseType);

        if (response.getStatusCode() != HttpStatus.OK || response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }
        return response;
    }
}

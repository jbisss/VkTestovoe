package ru.vktestovoe.jbisss.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.config.ApplicationConstants;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

abstract public class BaseService<DTO> implements RestCrudService<DTO> {

    private final RequestHandlerService requestHandlerService;
    protected final RestTemplate restTemplate;

    protected final String serviceUrl;

    protected BaseService(RequestHandlerService requestHandlerService, RestTemplate restTemplate, String serviceUrl) {
        this.requestHandlerService = requestHandlerService;
        this.restTemplate = restTemplate;
        this.serviceUrl = ApplicationConstants.Url.JSON_PLACE_HOLDER_URL + serviceUrl;
    }

    @SuppressWarnings("unchecked")
    private Class<DTO> getDtoType() {
        Type genericSuperclass = getClass().getGenericSuperclass();

        if (genericSuperclass instanceof ParameterizedType) {
            Type actualTypeArgument = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];

            if (actualTypeArgument instanceof Class) {
                return (Class<DTO>) actualTypeArgument;
            }
        }

        throw new IllegalArgumentException("Failed to determine the generic type.");
    }

    @Override
    public List<DTO> getList() {
        ParameterizedTypeReference<List<DTO>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<?> response = requestHandlerService.handleRequest(serviceUrl, HttpMethod.GET, null, responseType);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }
        return (List<DTO>) response.getBody();
    }

    @Override
    public DTO get(String dtoId) {
        final String entireUrl = serviceUrl + ApplicationConstants.SLASH + dtoId;
        ResponseEntity<?> response = requestHandlerService.handleRequest(entireUrl, HttpMethod.GET, null, getDtoType());

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }
        return (DTO) response.getBody();
    }

    @Override
    public DTO post(DTO dto) {
        HttpEntity<DTO> requestEntity = new HttpEntity<>(dto);
        ResponseEntity<?> response = requestHandlerService.handleRequest(serviceUrl, HttpMethod.POST, requestEntity, getDtoType());

        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }

        return (DTO) response.getBody();
    }

    @Override
    public DTO put(String dtoId, DTO dto) {
        final String entireUrl = serviceUrl + ApplicationConstants.SLASH + dtoId;
        HttpEntity<DTO> requestEntity = new HttpEntity<>(dto);
        ResponseEntity<?> response = requestHandlerService.handleRequest(entireUrl, HttpMethod.PUT, requestEntity, getDtoType());

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }
        return (DTO) response.getBody();
    }

    @Override
    public String delete(String dtoId) {
        final String entireUrl = serviceUrl + ApplicationConstants.SLASH + dtoId;
        ResponseEntity<?> response = requestHandlerService.handleRequest(entireUrl, HttpMethod.DELETE, null, getDtoType());

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }
        return HttpStatus.OK.toString();
    }
//
//    @Nonnull
//    public ResponseEntity<DTO> handleRequest(@Nonnull String url, @Nonnull HttpMethod httpMethod, @Nullable HttpEntity<DTO> requestEntity) {
//        ResponseEntity<DTO> response = restTemplate.exchange(url, httpMethod, requestEntity, getDtoType());
//
//        if (response.getStatusCode() != HttpStatus.OK || response.getStatusCode() != HttpStatus.CREATED) {
//            throw new RuntimeException(String.valueOf(response.getStatusCode()));
//        }
//        return response;
//    }
//
//    @Nonnull
//    public ResponseEntity<?> handleRequest(@Nonnull String url, @Nonnull HttpMethod httpMethod, @Nullable HttpEntity<?> requestEntity, ParameterizedTypeReference<?> responseType) {
//        ResponseEntity<?> response = restTemplate.exchange(url, httpMethod, requestEntity, responseType);
//
//        if (response.getStatusCode() != HttpStatus.OK || response.getStatusCode() != HttpStatus.CREATED) {
//            throw new RuntimeException(String.valueOf(response.getStatusCode()));
//        }
//        return response;
//    }
}

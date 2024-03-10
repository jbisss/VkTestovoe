package ru.vktestovoe.jbisss.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import ru.vktestovoe.jbisss.config.ApplicationConstants;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

abstract public class BaseService<DTO> implements RestCrudService<DTO> {

    protected final RequestHandlerService requestHandlerService;

    protected final String serviceUrl;

    protected BaseService(RequestHandlerService requestHandlerService, String serviceUrl) {
        this.requestHandlerService = requestHandlerService;
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
    @SuppressWarnings("unchecked")
    public List<DTO> getList() {
        ParameterizedTypeReference<List<DTO>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<?> response = requestHandlerService.handleRequest(serviceUrl, HttpMethod.GET, null, responseType);

        return (List<DTO>) response.getBody();
    }

    @Override
    public DTO get(String dtoId) {
        final String entireUrl = serviceUrl + ApplicationConstants.SLASH + dtoId;
        ResponseEntity<?> response = requestHandlerService.handleRequest(entireUrl, HttpMethod.GET, null, getDtoType());

        return getDtoType().cast(response.getBody());
    }

    @Override
    public DTO post(DTO dto) {
        HttpEntity<DTO> requestEntity = new HttpEntity<>(dto);
        ResponseEntity<?> response = requestHandlerService.handleRequest(serviceUrl, HttpMethod.POST, requestEntity, getDtoType());

        return getDtoType().cast(response.getBody());
    }

    @Override
    public DTO put(String dtoId, DTO dto) {
        final String entireUrl = serviceUrl + ApplicationConstants.SLASH + dtoId;
        HttpEntity<DTO> requestEntity = new HttpEntity<>(dto);
        ResponseEntity<?> response = requestHandlerService.handleRequest(entireUrl, HttpMethod.PUT, requestEntity, getDtoType());

        return getDtoType().cast(response.getBody());
    }

    @Override
    public String delete(String dtoId) {
        final String entireUrl = serviceUrl + ApplicationConstants.SLASH + dtoId;
        ResponseEntity<?> response = requestHandlerService.handleRequest(entireUrl, HttpMethod.DELETE, null, getDtoType());

        return response.getStatusCode().toString();
    }
}

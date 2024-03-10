package ru.vktestovoe.jbisss.service.api;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vktestovoe.jbisss.config.ApplicationConstants;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiRequestService {

    private final ApiCacheRequestHandlerService apiCacheRequestHandlerService;
    private final ApiHttpRequestHandlerService apiHttpRequestHandlerService;

    @Nonnull
    public ResponseEntity<?> handleRequest(@Nonnull String url, @Nonnull HttpMethod httpMethod, @Nullable HttpEntity<?> requestEntity, @Nonnull Class<?> type) {
        Optional<ResponseEntity<?>> response = getResponseValue(url, httpMethod, requestEntity, type);
        return response.orElseThrow(() -> new RuntimeException(ApplicationConstants.Exception.REQUEST_FAIL));
    }

    @Nonnull
    public ResponseEntity<?> handleRequest(@Nonnull String url, @Nonnull HttpMethod httpMethod, @Nullable HttpEntity<?> requestEntity, @Nonnull ParameterizedTypeReference<?> parameterizedTypeReference) {
        Optional<ResponseEntity<?>> response = getResponseValue(url, httpMethod, requestEntity, parameterizedTypeReference);
        return response.orElseThrow(() -> new RuntimeException(ApplicationConstants.Exception.REQUEST_FAIL));
    }

    @Nonnull
    private <TYPE> Optional<ResponseEntity<?>> getResponseValue(@Nonnull String url, @Nonnull HttpMethod httpMethod, @Nullable HttpEntity<?> requestEntity, @Nonnull TYPE type) {
        String key = defineKeyByRequestParameters(url, httpMethod, requestEntity);
        Optional<ResponseEntity<?>> valueFromCache = tryGetValueFromCache(key, httpMethod);

        if (valueFromCache.isPresent()) {
            return valueFromCache;
        }

        if (type instanceof Class<?> simpleType) {
            ResponseEntity<?> responseEntity = apiHttpRequestHandlerService.httpRequest(url, httpMethod, requestEntity, simpleType);
            apiCacheRequestHandlerService.updateCache(key, responseEntity);
            return Optional.of(responseEntity);
        } else if (type instanceof ParameterizedTypeReference<?> complicatedType) {
            ResponseEntity<?> responseEntity = apiHttpRequestHandlerService.httpRequest(url, httpMethod, requestEntity, complicatedType);
            apiCacheRequestHandlerService.updateCache(key, responseEntity);
            return Optional.of(responseEntity);
        }
        return Optional.empty();
    }

    @Nonnull
    private String defineKeyByRequestParameters(@Nonnull String url, @Nonnull HttpMethod httpMethod, @Nullable HttpEntity<?> requestEntity) {
        if (requestEntity != null) {
            return requestEntity.toString();
        }
        return httpMethod.toString() + url;
    }

    @Nonnull
    private Optional<ResponseEntity<?>> tryGetValueFromCache(@Nonnull String key, @Nonnull HttpMethod httpMethod) {
        if (httpMethod.equals(HttpMethod.GET)) {
            Optional<ResponseEntity<?>> valueCache = apiCacheRequestHandlerService.getFromCache(key);
            if (valueCache.isPresent()) {
                return valueCache;
            }
        }
        return Optional.empty();
    }
}

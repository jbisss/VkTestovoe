package ru.vktestovoe.jbisss.service.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ApiCacheRequestHandlerService {

    @Value("${api.cache.size}")
    private String cacheSize;

    private Map<String, ResponseEntity<?>> cache = new HashMap<>();

    public Optional<ResponseEntity<?>> getFromCache(String key) {
        ResponseEntity<?> value = cache.get(key);
        if (value != null) {
            return Optional.of(value);
        }
        return Optional.empty();
    }

    public void updateCache(String key, ResponseEntity<?> value) {
        if (cache.size() > Integer.parseInt(cacheSize)) {
            clearCache();
        }
        cache.put(key, value);
    }

    private void clearCache() {
        this.cache = new HashMap<>();
    }
}

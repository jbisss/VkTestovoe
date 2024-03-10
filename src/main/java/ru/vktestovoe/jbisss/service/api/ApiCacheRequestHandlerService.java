package ru.vktestovoe.jbisss.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vktestovoe.jbisss.config.ApplicationConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ApiCacheRequestHandlerService {

    private Map<String, ResponseEntity<?>> cache = new HashMap<>();

    public Optional<ResponseEntity<?>> getFromCache(String key) {
        ResponseEntity<?> value = cache.get(key);
        if (value != null) {
            return Optional.of(value);
        }
        return Optional.empty();
    }

    public void updateCache(String key, ResponseEntity<?> value) {
        if (cache.size() > ApplicationConstants.Cash.CASH_SIZE) {
            clearCache();
        }
        cache.put(key, value);
    }

    private void clearCache() {
        this.cache = new HashMap<>();
    }
}

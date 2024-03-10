package ru.vktestovoe.jbisss.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Aspect
@Component
public class RequestHandlerAspect {

    @Around("execution (* ru.vktestovoe.jbisss.service.api.ApiRequestService.*(..))")
    public Object logRequestsToApi(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        Object[] args = pjp.getArgs();
        String url = (String) args[0];
        HttpMethod httpMethod = (HttpMethod) args[1];

        Optional<Object> requestEntity = Arrays.stream(args)
                .filter(arg -> arg instanceof HttpEntity<?>)
                .findFirst();
        if (requestEntity.isPresent()) {
            log.info("Request: {} {} with body {} started", httpMethod, url, ((HttpEntity<?>) requestEntity.get()).getBody());
        } else {
            log.info("Request: {} {} started", httpMethod, url);
        }

        ResponseEntity<?> result;
        try {
            result = (ResponseEntity<?>) pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        HttpStatusCode statusCode = result.getStatusCode();
        long executionTime = System.currentTimeMillis() - startTime;
        log.info("Request: {} {} ended in {}ms with {}", httpMethod, url, executionTime, statusCode);
        return result;
    }
}

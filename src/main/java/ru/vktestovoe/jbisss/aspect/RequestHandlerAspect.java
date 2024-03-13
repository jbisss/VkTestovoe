package ru.vktestovoe.jbisss.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
            log.info("Request: {} {} with body {} started by user {}", httpMethod, url, ((HttpEntity<?>) requestEntity.get()).getBody(), getCurrentUserDetails());
        } else {
            log.info("Request: {} {} started by user {}", httpMethod, url, getCurrentUserDetails());
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

    private String getCurrentUserDetails() {
        StringBuilder userDetailsResult = new StringBuilder();
        final String WITH_ROLES = " with roles: ";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                String username = userDetails.getUsername();
                userDetailsResult.append(username);
                if (!userDetails.getAuthorities().isEmpty()) {
                    userDetailsResult.append(WITH_ROLES);
                    userDetails.getAuthorities().forEach(authority -> userDetailsResult.append(authority.getAuthority()));
                }
            }
        }
        return userDetailsResult.toString();
    }
}

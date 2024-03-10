package ru.vktestovoe.jbisss.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RequestHandlerAspect {

    @Around("execution (* ru.vktestovoe.jbisss.service.RequestHandlerService.handleRequest(..))")
    public Object logRequestsToApi(ProceedingJoinPoint pjp) throws Throwable {
        PessimisticLockingFailureException lockFailureException;
        log.info("I'm here");
        try {
            return pjp.proceed();
        }
        catch(PessimisticLockingFailureException ex) {
            lockFailureException = ex;
        }
        throw lockFailureException;
    }
}

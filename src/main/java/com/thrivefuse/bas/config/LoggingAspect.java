package com.thrivefuse.bas.config;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // Pointcut that matches all repositories, services, and Web REST endpoints.
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    // Pointcut that matches all Spring beans in the application's main packages.
    @Pointcut("within(com.thrivefuse.bas.controller..*)" +
            " || within(com.thrivefuse.bas.service..*)" +
            " || within(com.thrivefuse.bas.repository..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    // Advice that logs methods before they are executed.
    @Before("applicationPackagePointcut() && springBeanPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    // Advice that logs methods after they have returned.
    @AfterReturning(pointcut = "applicationPackagePointcut() && springBeanPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e);
    }
}
package com.ing.ls.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(public * com.ing.ls..*.*(..))")
    public void methodLogs() {}

    @Around("methodLogs()")
    public Object logMethodEntryAndExit(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        logger.debug("Entering method [{}] with arguments: {}", joinPoint.getSignature(), Arrays.toString(args));

        Object result = joinPoint.proceed();
        logger.debug("Exiting method [{}] with result: {}", joinPoint.getSignature(), result);

        return result;
    }

    @Pointcut("execution(public * com.ing.ls.dao.*.*(..))")
    public void methodExecution() {}

    @Around("methodExecution()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long elapsedTime = System.currentTimeMillis() - startTime;
        logger.debug("Method [{}] executed in {} ms", joinPoint.getSignature(), elapsedTime);
        return result;
    }
}

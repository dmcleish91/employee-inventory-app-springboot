package com.mcleish.app.employeecrudapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AspectConfig {

    /*
    *  Logging
    *  Exception Handling
    *  TimeTaken
     */

    private final Logger log = LoggerFactory.getLogger(AspectConfig.class);

    @Before(value = "execution(* com.mcleish.app.employeecrudapp.controller.*.*(..))")
    public void logStatementBefore(JoinPoint joinPoint) {
        log.info("Executing {}", joinPoint);
    }
}

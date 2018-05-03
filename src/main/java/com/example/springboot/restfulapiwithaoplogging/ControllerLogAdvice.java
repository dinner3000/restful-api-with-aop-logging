package com.example.springboot.restfulapiwithaoplogging;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLogAdvice {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private Gson gson = new Gson();

    @Autowired
    private ControllerLogRecord logRecord;

    @Pointcut("execution(public * com.example.springboot.restfulapiwithaoplogging.*.*(..)) && @annotation(com.example.springboot.restfulapiwithaoplogging.ControllerLog)")
    public void controllerLog(){}

    @AfterReturning(returning = "retVal", pointcut = "controllerLog()")
    public void afterReturning(JoinPoint joinPoint, Object retVal){
        logger.debug("###AjaxControllerLogAdvice - afterReturning()");

        logRecord.loadContextInfo(joinPoint);
        logRecord.setResponse(retVal);

        logger.info(gson.toJson(logRecord));
    }

    @AfterThrowing(throwing = "e", pointcut = "controllerLog()")
    public void afterThrowing(JoinPoint joinPoint, Throwable e){
        logger.debug("###AjaxControllerLogAdvice - afterThrowing()");

        logRecord.loadContextInfo(joinPoint);
        logRecord.setResponse(null);

        logger.error(e.getMessage(), e);
        logger.info(gson.toJson(logRecord));
    }
}

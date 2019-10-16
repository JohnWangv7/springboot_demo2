package com.esen.swglpt.common.aspect;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangdong
 * 操作日志Aspect
 */
@Aspect
@Component
public class MapperLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(MapperLogAspect.class);

    /**
     * 以mapper包下定义的所有请求为切入点
     */
    @Pointcut("execution(public * com.esen.swglpt.mapper..*.*(..))")
    public void mapperLog() {
    }

    /**
     * 在切点前织入
     */
    @Before("mapperLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印调用mapper的全路径以及执行方法
        logger.info("Class Method           :{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    /**
     * 在切点之后织入
     */
    @After("mapperLog()")
    public void doAfter() throws Throwable {
        logger.info("");
    }

    /**
     * 环绕
     */
    @Around("mapperLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        logger.info("Response Args          :{}", new Gson().toJson(result));
        // 执行耗时
        logger.info("Time-Consuming         :{} ms", System.currentTimeMillis() - startTime);
        return result;
    }
}

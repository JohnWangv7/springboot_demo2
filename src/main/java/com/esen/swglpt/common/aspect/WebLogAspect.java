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
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 以Controller包下定义的所有请求为切入点
     */
    @Pointcut("execution(public * com.esen.swglpt.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 在切点前织入
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        logger.info("============================== Start ==============================");
        // 打印请求Url
        logger.info("URL                    :{}", request.getRequestURL().toString());
        // 打印Http method
        logger.info("HTTP Method            :{}", request.getMethod());
        // 打印调用Controller的全路径以及执行方法
        logger.info("Class Method           :{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的IP
        logger.info("IP                     :{}", request.getRemoteAddr());
        // 打印请求入参
        logger.info("Request Args           :{}", new Gson().toJson(joinPoint.getArgs()));
    }

    /**
     * 在切点之后织入
     */
    @After("webLog()")
    public void doAfter() throws Throwable {
        logger.info("============================== End ==============================");
        logger.info("");
    }

    /**
     * 环绕
     */
    @Around("webLog()")
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